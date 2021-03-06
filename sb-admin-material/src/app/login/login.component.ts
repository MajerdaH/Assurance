import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  bodyFrm:any;
    signupfrm:any;
    registerfrm:any;
    memberId:String;
    role:String;
    showRegister=false;
    showLogin=true;
    private _loginUrl = 'http://localhost:8080/login'; 
    showPassword=false;
    showFirstValidate=true;
    private _InfoUrl ='http://localhost:8080/getMemberBy/';
    private _registerUrl='http://localhost:8080/addUser';


    constructor(private router: Router, private formb: FormBuilder, private _http: HttpClient) {
        this.signupfrm =new FormGroup({
            email: new FormControl(),
            password: new FormControl()
            });
        this.registerfrm =new FormGroup({
            ponum: new FormControl(),
            mat: new FormControl( ),
            password: new FormControl(),
            confirmpassword: new FormControl(),
            login: new FormControl()
            });
    }
    private body: any;
    private registerBody: any;

    ngOnInit() {}

    onLogin() {
      console.log("onLogin");
       this.body= {};
       this.body['login'] = this.signupfrm.value.email;
       this.body['password'] = this.signupfrm.value.password;
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


  OnRegister(){
  this.showRegister=true;
  this.showLogin=false;
  }

  OnCheckMember(){
   
    this._http.get(this._InfoUrl+this.registerfrm.value.mat+'/'+this.registerfrm.value.ponum).subscribe(info =>{
      console.log(info)
      this.registerBody=info;
      if(this.registerBody.numP!='0'){ this.showPassword=true;
        this.showFirstValidate=false;}
  });}

  responseRegister:any;
  OnSaveInfos(){
    this.bodyFrm= {};
    this.bodyFrm['login'] = this.registerfrm.value.login;
    this.bodyFrm['password'] = this.registerfrm.value.password;
    this.bodyFrm['mat'] = this.registerfrm.value.mat;
    this.bodyFrm['ponum'] = this.registerfrm.value.ponum;
    
      this._http.post(this._registerUrl,
       this.bodyFrm,
         {
           headers: new HttpHeaders()
             .set('Content-Type', 'application/json')
         }
       ).subscribe(resp => {console.log(resp)
         this.responseRegister=resp;
       console.log(this.responseRegister.ponum)  
        if(this.responseRegister.ponum==1){   this.showRegister=false;
          this.showLogin=true;}});

  }

}
