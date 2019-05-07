import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    signupfrm:any;
    memberId:String;
    role:String;
    private _loginUrl = 'http://localhost:8080/login'; 
    constructor(private router: Router, private formb: FormBuilder, private _http: HttpClient) {
        this.signupfrm =new FormGroup({
            email: new FormControl(),
            password: new FormControl()
            });
    }
    private body: any;

    ngOnInit() {}

    onLogin() {
 
       console.log(this.signupfrm.value.email);
     //  this.body = new HttpParams()
       //.set('login', this.signupfrm.value.email)
       //.set('password', this.signupfrm.value.password);
       this.body= {};
       this.body['login'] = this.signupfrm.value.email;
       this.body['password'] = this.signupfrm.value.password;
       //'{"login":"'
       //+this.signupfrm.value.email+'","password":"'+this.signupfrm.value.password+'"}';
       
         this._http.post(this._loginUrl,
          this.body,
            {
              headers: new HttpHeaders()
                .set('Content-Type', 'application/json')
            }
          )
        .pipe(
        catchError(this.handleError) // then handle the error
       ).subscribe(resp => {
      if( resp['ponum']!='0'){
          let ponum=resp['ponum'];
          let mat=resp['mat'];
          console.log(mat +"    >>>    "+ponum)
          this.role=resp['role'];
          localStorage.setItem('isLoggedin', 'true');
          localStorage.setItem('ponum',ponum);
          localStorage.setItem('mat',mat);

          if(this.role=='member'){
        this.router.navigate(['/dashboard']);}
      }
      else {
        localStorage.setItem('isLoggedin', 'false');
        this.router.navigate(['/login']);
      }
    });
          
    }

  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }
}
