import {Component, inject, signal} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {KlijentService} from '../../core/klijent/klijent';
import {KlijentPravnoLiceRequest} from '../../core/klijent/klijent.models';

@Component({
  selector: 'app-klijent-forma',
  imports: [RouterLink, ReactiveFormsModule, ReactiveFormsModule],
  templateUrl: './klijent-forma.html',
})
export class KlijentForma {
    private fb= inject(FormBuilder);
    private klijentService = inject(KlijentService);
    private router = inject(Router);
    greska = signal<string | null>("")

    form=this.fb.group({
      naziv:['',[Validators.required,Validators.maxLength(255)]],
      sjediste:['',[Validators.required,Validators.maxLength(255)]],
      pib:['',[Validators.required,Validators.maxLength(20)]],
      sifraDjelatnosti:['',[Validators.required,Validators.maxLength(20)]],
      datumOsnivanjaFirme:[''],
      datumUPO:[''],
      telefon:[''],
      razlogPoslovnogOdnosa:['', Validators.maxLength(300)],
      email:['',[Validators.email,Validators.maxLength(100)]],
      resident:[true]});

  sacuvaj(){
    const v = this.form.getRawValue();

    const klijent: KlijentPravnoLiceRequest = {
      naziv: v.naziv ?? '',
      sjediste : v.sjediste ??'',
      pib: v.pib ??'',
      sifraDjelatnosti: v.sifraDjelatnosti ?? '',
      telefon : v.telefon ?? '',
      email: v.email ?? '',
      razlogPoslovnogOdnosa: v.razlogPoslovnogOdnosa ?? '',

      datumOsnivanjaFirme: v.datumOsnivanjaFirme || null,
      datumUPO: v.datumUPO || null,
      resident: v.resident ?? true
    };
    this.klijentService.create(klijent).subscribe({
      next: () => this.router.navigate(['/klijenti']),
      error: () =>this.greska.set("greska pri unosu",)
    })
  }

}


