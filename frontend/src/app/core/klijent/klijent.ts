import {inject, Service} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {KlijentPravnoLice, KlijentPravnoLiceRequest} from './klijent.models';

@Service()
export class KlijentService {
  private http=inject(HttpClient);
  private readonly url=`${environment.apiUrl}/klijent/pl`;

  getAll(): Observable<KlijentPravnoLice[]>{
    return this.http.get<KlijentPravnoLice[]>(`${this.url}/all`);
  }
  create(klijentPravnoLiceRequest: KlijentPravnoLiceRequest): Observable<KlijentPravnoLice>{
    return this.http.post<KlijentPravnoLice> (this.url ,klijentPravnoLiceRequest);
  }


}
