import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-prijava',
  imports: [RouterLink],
  templateUrl: './prijava.html',
  styleUrl: './prijava.css',
})
export class Prijava {
  // constructor() {console.log(environment.apiUrl)  samo mali test da vidim radi li env
  // }
}
