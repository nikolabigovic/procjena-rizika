import {Component, inject, signal} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {KlijentService} from '../../core/klijent/klijent';
import {KlijentPravnoLiceRequest, STATUSI_FIZICKOG_LICA} from '../../core/klijent/klijent.models';
import {FizickoLice} from '../../core/klijent/fizicko-lice';
import {forkJoin} from 'rxjs';

@Component({
  selector: 'app-klijent-forma',
  imports: [RouterLink, ReactiveFormsModule, ReactiveFormsModule],
  templateUrl: './klijent-forma.html',
})
export class KlijentForma {
  private fb = inject(FormBuilder);
  private klijentService = inject(KlijentService);
  private router = inject(Router);
  greska = signal<string | null>("")
  private fizickoLiceService = inject(FizickoLice)
  statusi = STATUSI_FIZICKOG_LICA;

  private napraviLice() {
    return this.fb.group({
      ime: ['', [Validators.required, Validators.maxLength(100)]],
      prezime: ['', [Validators.required, Validators.maxLength(100)]],
      maticniBr: ['', [Validators.required, Validators.pattern(/^\d{13}$/)]],
      datumRodjenja: ['', Validators.required],
      mjestoPrebivalista: ['', Validators.maxLength(255)],
      statusId: [1, Validators.required],
      isPel: [false],
    });
  }

  form = this.fb.group({
    naziv: ['', [Validators.required, Validators.maxLength(255)]],
    sjediste: ['', [Validators.required, Validators.maxLength(255)]],
    pib: ['', [Validators.required, Validators.maxLength(20)]],
    sifraDjelatnosti: ['', [Validators.required, Validators.maxLength(20)]],
    datumOsnivanjaFirme: [''],
    datumUPO: [''],
    telefon: [''],
    razlogPoslovnogOdnosa: ['', Validators.maxLength(300)],
    email: ['', [Validators.email, Validators.maxLength(100)]],
    resident: [true],
    fizickaLica: this.fb.array([this.napraviLice()])
  });

  sacuvaj() {
    const v = this.form.getRawValue();

    const klijent: KlijentPravnoLiceRequest = {
      naziv: v.naziv ?? '',
      sjediste: v.sjediste ?? '',
      pib: v.pib ?? '',
      sifraDjelatnosti: v.sifraDjelatnosti ?? '',
      telefon: v.telefon ?? '',
      email: v.email ?? '',
      razlogPoslovnogOdnosa: v.razlogPoslovnogOdnosa ?? '',

      datumOsnivanjaFirme: v.datumOsnivanjaFirme || null,
      datumUPO: v.datumUPO || null,
      resident: v.resident ?? true
    };
    this.klijentService.create(klijent).subscribe({
      next: (sacuvan) => this.sacuvajLica(sacuvan.id),
      error: () => this.greska.set('Greška pri čuvanju klijenta.'),
    });
  }

  dodajLice() {
    this.form.controls.fizickaLica.push(this.napraviLice());
  }

  ukloniLice(i: number) {
    this.form.controls.fizickaLica.removeAt(i);
  }

  private sacuvajLica(pravnoLiceId: number) {
    const lica = this.form.getRawValue().fizickaLica;

    if (lica.length === 0) {
      this.router.navigate(['/klijenti']);
      return;
    }

    const zahtjevi = lica.map((l) =>
      this.fizickoLiceService.create({
        ime: l.ime ?? '',
        prezime: l.prezime ?? '',
        maticniBr: l.maticniBr ?? '',
        datumRodjenja: l.datumRodjenja ?? '',
        mjestoPrebivalista: l.mjestoPrebivalista ?? '',
        isPel: l.isPel ?? false,
        statusId: l.statusId ?? 1,
        pravnoLiceId: pravnoLiceId,
      })
    );

    forkJoin(zahtjevi).subscribe({
      next: () => this.router.navigate(['/klijenti']),
      error: () => this.greska.set('Klijent je sačuvan, ali fizička lica nisu. Provjerite matične brojeve.'),
    });

  }

}
