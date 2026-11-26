import {Component, inject, signal} from '@angular/core';
import {AuthService} from '../../core/auth/auth-service';
import {Router, RouterLink} from '@angular/router';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  selector: 'app-registracija',
  styleUrl: './registracija.css',
  templateUrl: './registracija.html',
})
export class Registracija {

  private authService = inject(AuthService);
  private router= inject(Router);

  greska = signal<string | null>(null)


  form= new FormGroup({
    firma: new FormGroup({
      nazivFirme : new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      sjediste: new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      pib:new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      sifraDjelatnosti: new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      datumOsnivanja: new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      telefon : new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      email: new FormControl('', {nonNullable:true, validators:[Validators.required, Validators.email]}),
      posljedice:new FormControl(4,{nonNullable:true, validators:[Validators.required, Validators.min(1), Validators.max(5)]}),
    }),
    korisnik: new FormGroup({
      ime : new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      prezime: new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      username: new FormControl('', {nonNullable:true, validators:[Validators.required]}),
      email: new FormControl('', {nonNullable:true, validators:[Validators.required, Validators.email]}),
      password: new FormControl('', {nonNullable:true, validators:[Validators.required,  Validators.minLength(8)]}),
    }),
  });

  registrujSe():void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.greska.set(null)
    const{firma,korisnik}=this.form.getRawValue();
    this.authService.register(this.form.getRawValue()).subscribe({
      next: () => {this.router.navigateByUrl('/prijava');},
      error: err => {this.greska.set("Neuspjesna registracija")}
    })
  }
}
