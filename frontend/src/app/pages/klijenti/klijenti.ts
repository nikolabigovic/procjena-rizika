import {Component, inject, OnInit, signal} from '@angular/core';
import { RouterLink } from '@angular/router';
import {KlijentService} from '../../core/klijent/klijent';
import {KlijentPravnoLice, KlijentPravnoLiceRequest} from '../../core/klijent/klijent.models';

@Component({
  selector: 'app-klijenti',
  imports: [RouterLink],
  templateUrl: './klijenti.html',
})
export class Klijenti implements OnInit {
  private klijentService = inject(KlijentService);

  klijenti = signal<KlijentPravnoLice[]>([]);
  greska = signal<string | null>(null);

  ngOnInit():void {
    this.klijentService.getAll().subscribe({
      next: (lista) =>this.klijenti.set(lista),
      error: ()=>this.greska.set("greska pri ucitavanju klijenata.")
    })
  }
}

