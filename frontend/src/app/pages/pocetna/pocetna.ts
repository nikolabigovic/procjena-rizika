import {Component, inject} from '@angular/core';
import {Router, RouterLink} from '@angular/router';

@Component({
  imports: [RouterLink],
  selector: 'app-pocetna',
  styleUrl: './pocetna.css',
  templateUrl: './pocetna.html',
})
export class Pocetna {
  private router= inject(Router);

}
