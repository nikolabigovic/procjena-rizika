import {inject, Service} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ProcjenaRizikaRequest, ProcjenaRizikaResponse} from './procjena.models';
import {Observable} from 'rxjs';

@Service()
export class Procjena {
  private http=inject(HttpClient);
  private readonly url=`${environment.apiUrl}/pr`;


  create(request:ProcjenaRizikaRequest):Observable<ProcjenaRizikaResponse>{
    return this.http.post<ProcjenaRizikaResponse>(this.url,request,)
  }

  getPoslednje(): Observable<ProcjenaRizikaResponse[]>{
    return this.http.get<ProcjenaRizikaResponse[]>(`${this.url}/poslednje`);
  }

}
