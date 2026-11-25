import {Component, inject, OnInit, signal} from '@angular/core';
import {Procjena} from '../../core/procjena/procjena';
import {IzlozenostDashboard} from '../../core/procjena/procjena.models';

@Component({
  selector: 'app-izlozenost',
  templateUrl: './izlozenost.html',
})
export class Izlozenost implements OnInit {

  private procjenaService = inject(Procjena);

  podaci = signal<IzlozenostDashboard | null >(null);
  greska=signal<string | null>(null);
  ngOnInit() {
    this.procjenaService.getDashboard().subscribe({
      next: (d) => this.podaci.set(d),
      error: () => this.greska.set("greska u ucitavanju dashboard-a"),
    });
  }
}
