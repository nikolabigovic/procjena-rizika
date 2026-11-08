import {Component, inject, signal} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../core/auth/auth-service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-prijava',
  imports: [ReactiveFormsModule],
  templateUrl: './prijava.html',
  styleUrl: './prijava.css',

})
export class Prijava {
  private authService = inject(AuthService);
  private router = inject(Router);

  form = new FormGroup({
    username: new FormControl('', {nonNullable: true, validators: [Validators.required]}),
    password: new FormControl('', {nonNullable: true, validators: [Validators.required]}),
    rememberMe: new FormControl(false, {nonNullable: true}),

  });
  greska = signal<string | null>(null)

   prijaviSe() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.greska.set(null);
    const{username, password, rememberMe}=this.form.getRawValue();
    this.authService.login(username, password, rememberMe).subscribe({
      next: () => {this.router.navigate(['/klijenti']);},
      error: () => {this.greska.set("Pogresno korisnicko ime ili lozinka");},
    })
  }
}





