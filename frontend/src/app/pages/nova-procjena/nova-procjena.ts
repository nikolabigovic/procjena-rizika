import {Component, OnInit, inject, signal, computed} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {KlijentService} from '../../core/klijent/klijent';
import {KlijentPravnoLice} from '../../core/klijent/klijent.models';
import {Indikator} from '../../core/procjena/indikator';
import {IndikatorRizika, ProcjenaRizikaRequest, ProcjenaRizikaResponse} from '../../core/procjena/procjena.models';
import {Procjena} from '../../core/procjena/procjena';
import {DatePipe, DecimalPipe} from '@angular/common';

@Component({
  selector: 'app-nova-procjena',
  templateUrl: './nova-procjena.html',
  styleUrl: './nova-procjena.css',
  imports: [ReactiveFormsModule, DecimalPipe, DatePipe],
})
export class NovaProcjena implements OnInit {
  private fb = inject(FormBuilder);
  private klijentService = inject(KlijentService);
  private indikatorService = inject(Indikator)

  klijenti = signal<KlijentPravnoLice[]>([]);
  greska=signal<string | null>(null);
  indikatori = signal<IndikatorRizika[]>([]);
  private procjenaService = inject(Procjena);
  rezultat = signal<ProcjenaRizikaResponse | null>(null);
  salje = signal(false);

  form= this.fb.group({
    klijentPravnoLiceId: [null as number | null, Validators.required],
    ocjene: this.fb.array<number | null>([]),
    automatskiRizik:[false],
    obrazlozenje:[''],
  });

  grupe= computed(()=>{
    const mapa = new Map<number, string>();
    for (const ind of this.indikatori()){
      mapa.set(ind.grupaFaktoraRizikaId, ind.grupaFaktoraRizikaNaziv);
    }
    return[...mapa].map(([id,naziv])=>({id,naziv})) //pretvara map u niz
  })

  ngOnInit() {
    this.klijentService.getAll().subscribe({
      next: (lista )=>this.klijenti.set(lista),
      error:()=>this.greska.set("greska u unosu procjene")
    })

    this.indikatorService.getAll().subscribe({
      next: (lista) => {
        this.indikatori.set(lista)
        for (const ind of lista) {
          this.form.controls.ocjene.push(this.fb.control<number | null>(null, Validators.required));
        }
      },
      error: () => this.greska.set('greska pri ucitavnajju indikatora!'),
    });
  }

  save(){
    if(this.form.invalid){
      this.greska.set("izaberite klijenta i ocjenite sve indikatore");
      return;
    }
    const auto = this.form.controls.automatskiRizik.value;
    const obrazlozenje = this.form.controls.obrazlozenje.value?.trim();

    if (auto && !obrazlozenje) {
      this.greska.set('Unesite obrazloženje za ručno postavljen visok rizik.');
      return;
    }

    const v = this.form.getRawValue();

    const request: ProcjenaRizikaRequest = {
      klijentPravnoLiceId: v.klijentPravnoLiceId!,
      obrazlozenje: obrazlozenje || null,
      automatskiRizik:auto ?? false,
      listaOcjena: this.indikatori().map((ind, i)=> ({
        indikatorRizikaId:ind.id,
        ocjena: v.ocjene[i]!,
      })),


    };

    this.greska.set(null);
    this.salje.set(true);

    this.procjenaService.create(request).subscribe({
      next:(odgovor)=>{
        this.rezultat.set(odgovor);
        this.salje.set(false);
      },
      error:()=> {
        this.greska.set("greska pri cuvanju procjene!");
        this.salje.set(false);
      }

    });
  }



  postaviSveNa1(){
    for(const kontrola of this.form.controls.ocjene.controls){
      kontrola.setValue(1);
    }
  }

  razlozi = [
    'Klijent ili povezano lice je politički eksponirano lice (PEP).',
    'Nije moguće pouzdano utvrditi stvarnog vlasnika.',
    'Klijent posluje sa visokorizičnim zemljama ili jurisdikcijama.',
    'Postoji sumnja na pranje novca ili finansiranje terorizma.',
    'Negativne informacije o klijentu iz javnih izvora.',
    'Neuobičajene ili složene transakcije bez jasne ekonomske svrhe.',
  ];

  izaberiRazlog(razlog:string){
    this.form.controls.obrazlozenje.setValue(razlog);
  }




}
