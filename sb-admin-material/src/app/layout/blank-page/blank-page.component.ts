import { Component, OnInit } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { FormGroup, FormControl } from '@angular/forms';

import { HttpClient, HttpHeaders } from '@angular/common/http';

const URL = 'http://localhost:3000/api/upload';
@Component({
    selector: 'app-blank-page',
    templateUrl: './blank-page.component.html',
    styleUrls: ['./blank-page.component.scss']
})
export class BlankPageComponent implements OnInit {

    name:any;
    date:any;
    type:any;
    pwdFormGroup:any;
    private _urlAdd='http://localhost:8080/addPartner'
    public uploader: FileUploader = new FileUploader({url: URL, itemAlias: 'photo'});
    constructor(private _http: HttpClient) {
        this.pwdFormGroup =new FormGroup({
            name: new FormControl(),
            type: new FormControl(),
            date: new FormControl()
            });
    }

    ngOnInit() {
        this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
        this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
             console.log('ImageUpload:uploaded:', item, status, response);
             alert('File uploaded successfully');
         };
    }

pwdBody:any;
    addPartner(){
        this.pwdBody={}

  this.pwdBody['mat']=localStorage.getItem('mat');
  this.pwdBody['ponum']=localStorage.getItem('ponum');
  this.pwdBody['type']=this.pwdFormGroup.value.type;
  this.pwdBody['date']=this.pwdFormGroup.value.date;
  this.pwdBody['name']=this.pwdFormGroup.value.name;
 // this.pwdBody['newPassword']=this.pwdFormGroup.value.new;
  //this.pwdBody['oldPassword']=this.pwdFormGroup.value.old;
 let d:Date;
 d=this.pwdFormGroup.value.date;
 console.log(d)
  console.log(this.pwdFormGroup.value.name)
  this._http.post(this._urlAdd,
    this.pwdBody,
      {
        headers: new HttpHeaders()
          .set('Content-Type', 'application/json')
      }
        ).subscribe(resp => {console.log(resp);});
    }


    
}
