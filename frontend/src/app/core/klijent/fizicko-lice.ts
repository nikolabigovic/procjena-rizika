import {inject, Service} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {KlijentFizickoLiceRequest, KlijentFizickoLiceResponse, STATUSI_FIZICKOG_LICA} from './klijent.models';
import {Observable} from 'rxjs';

@Service()
export class FizickoLice {
  private http=inject(HttpClient);
  private readonly url=`${environment.apiUrl}/klijent/fl`;



  create(request: KlijentFizickoLiceRequest): Observable<KlijentFizickoLiceResponse>{
    return this.http.post<KlijentFizickoLiceResponse>(this.url, request);
  }
}
