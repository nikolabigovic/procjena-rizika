import { Routes } from '@angular/router';
import { Shell } from './layout/shell';
import { Prijava } from './pages/prijava/prijava';
import { Klijenti } from './pages/klijenti/klijenti';
import { KlijentForma } from './pages/klijent-forma/klijent-forma';
import { NovaProcjena } from './pages/nova-procjena/nova-procjena';
import { Istorija } from './pages/istorija/istorija';
import { Izlozenost } from './pages/izlozenost/izlozenost';
import { Admin } from './pages/admin/admin';

// Rute služe samo da se može kliktati između ekrana dizajna.
// `data.naslov` je tekst koji se prikazuje u plavoj gornjoj traci.
export const routes: Routes = [
  { path: 'prijava', component: Prijava },
  {
    path: '',
    component: Shell,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'klijenti' },
      { path: 'klijenti', component: Klijenti, data: { naslov: 'Klijenti' } },
      { path: 'klijenti/novi', component: KlijentForma, data: { naslov: 'Novi klijent' } },
      { path: 'procjena/nova', component: NovaProcjena, data: { naslov: 'Nova procjena rizika' } },
      { path: 'istorija', component: Istorija, data: { naslov: 'Istorija procjena' } },
      { path: 'izlozenost', component: Izlozenost, data: { naslov: 'Ukupna izloženost obveznika' } },
      { path: 'admin', component: Admin, data: { naslov: 'Administracija' } },
    ],
  },
  { path: '**', redirectTo: '' },
];
