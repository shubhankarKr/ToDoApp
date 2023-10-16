import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { User } from '../model/User';
import { Router } from '@angular/router';
import { getCookie } from 'typescript-cookie';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  user = new User();
  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    this.user=req.body
    
    let httpHeaders = req.headers
    console.log('Authorization interceptor '+httpHeaders.get('Authorization'));
   
    
    // httpHeaders = httpHeaders.append('Authorization', 'Basic ' + window.btoa('Clerk' + ':' + 'a'));
    let xsrf = sessionStorage.getItem('XSRF-TOKEN');
    if(xsrf){
      httpHeaders = httpHeaders.append('X-XSRF-TOKEN', xsrf);  
    }
    console.log('Authorization X-XSRF-TOKEN '+httpHeaders.get('X-XSRF-TOKEN'));
    httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');
    const xhr = req.clone({
      headers: httpHeaders
    });
  return next.handle(xhr).pipe(tap(
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 401) {
            return;
          }
          this.router.navigate(['/']);
        }
      }));
  }
}
