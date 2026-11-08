import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {AuthService} from './auth-service';
import {inject} from '@angular/core';
import {Router} from '@angular/router';
import {catchError, throwError} from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();
  const router = inject(Router)

  let zahtjev = req;
  if(token) {
      zahtjev = req.clone({
      setHeaders: {Authorization: 'Bearer ' + token},
    });
  }
    return next(zahtjev).pipe(
      catchError((err: HttpErrorResponse) => {
        if(err.status===401){
          authService.logout();
          router.navigate(['/prijava'])
        }
        return throwError(()=>err);
      })
    );
};
