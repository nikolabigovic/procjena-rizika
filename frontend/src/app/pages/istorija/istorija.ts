import {Component, inject, OnInit, signal} from '@angular/core';

import {Procjena} from '../../core/procjena/procjena';
import {ProcjenaRizikaResponse} from '../../core/procjena/procjena.models';

import {DatePipe, DecimalPipe} from '@angular/common';

@Component({
  selector: 'app-istorija',
  imports: [ DecimalPipe, DatePipe],
  templateUrl: './istorija.html',
})
export class Istorija implements OnInit {
  private procjenaService= inject(Procjena);

  procjene = signal<ProcjenaRizikaResponse[]>([]);
  greska=signal<string | null>(null);

  ngOnInit(){
    this.procjenaService.getPoslednje().subscribe({
      next:(lista)=>this.procjene.set(lista),
      error:()=> this.greska.set("greska pri izvlacenju istorije procjena")
    });
  }
}
