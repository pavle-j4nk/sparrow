import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';

import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Router} from "@angular/router";

@Injectable()
export class HttpConfigInterceptor implements HttpInterceptor {

  private ignoreList: string[] = [
    "/api/user/me"
    ];

  constructor(private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        var ignore = false;
        this.ignoreList.forEach(function (ignoreUrl) {
          if (error.url.indexOf(ignoreUrl) != -1)
            ignore = true;
        });

        if (!ignore && error.status == 401) {
          this.router.navigateByUrl("/login");
        }

        return throwError(error);
      }));
  }
}
