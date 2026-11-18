import {inject, Service} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {IndikatorRizika} from './procjena.models';

@Service()
export class Indikator {
  private http=inject(HttpClient);
  private readonly url=`${environment.apiUrl}/indikator`;

  getAll():Observable<IndikatorRizika[]>{
    return this.http.get<IndikatorRizika[]>(`${this.url}/all`)
  }
}
