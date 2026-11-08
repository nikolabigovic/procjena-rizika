import { Service, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { JwtResponse, LoginRequest } from './auth.models';

@Service()
export class AuthService {
  private http = inject(HttpClient);
  private readonly TOKEN_KEY = 'token';

  login(username: string, password: string, rememberMe: boolean): Observable<JwtResponse> {
    const body:LoginRequest = {username, password};

    return this.http
      .post<JwtResponse>(`${environment.apiUrl}/auth/login-jwt`, body, {params:{rememberMe}})
      .pipe(tap(res=> localStorage.setItem(this.TOKEN_KEY, res.token)));
}

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }
  isLoggedIn(): boolean {
    return !!this.getToken(); //zato sto ako je token prazan string i dalje me pustao da pristupim, zato ce sada da baza false
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }


}




