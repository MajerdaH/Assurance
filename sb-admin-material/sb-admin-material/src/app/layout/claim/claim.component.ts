import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import {  FileUploader, FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';
import { HttpClient } from '@angular/common/http';
export interface ClaimElement {
  postDate: string;
  type: string;
  title: string;
  description: string;
  status: string;
}

const ELEMENT_DATA: ClaimElement[] = [
  { postDate: '31/03/2019', type: 'Réclamation', title: 'Dossier non traité',description:'Bonjour, mon dossier reste non traité depuis Decembre 2018.', status: 'En cours' },
  { postDate: '31/03/2019', type: 'Suggestion', title: 'Envoie de messages',description:'Bonjour, Je vous propose d\'envoyer les notification de versement des remboursement par messages sur nos téléphones. Merci', status: 'En cours' }
];

const URL = 'http://localhost:3000/api/upload';
@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.scss']
})
export class ClaimComponent implements OnInit {
  name:string;
claims:any;
showAddSuggestion:boolean;
showClaims:boolean;
displayedColumns = ['bull','careDate','setDate','cPolicy', 'progress'];
dataSource: MatTableDataSource<any>;
private _InfoUrl ='http://localhost:8080/getMemberClaimsBy/';
public uploader: FileUploader = new FileUploader({url: URL, itemAlias: 'photo'});

constructor(private _http: HttpClient) {
      this.name=localStorage.getItem('memberName');
  console.log(localStorage.getItem('ponum'));
  let ponum=localStorage.getItem('ponum');
  console.log(localStorage.getItem('mat'));
  let mat=localStorage.getItem('mat');

  this._http.get(this._InfoUrl+mat+'/'+ponum).subscribe(info =>{ this.claims=info;
      console.log(this.claims)
  });
  this.getAllClaims(); }

  ngOnInit() {
      this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
      this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
           console.log('ImageUpload:uploaded:', item, status, response);
           alert('File uploaded successfully');
       };

    this.showAddSuggestion=false;
    this.showClaims=true;
  }

addClaim(){
this.showAddSuggestion=!this.showAddSuggestion;
this.showClaims=!this.showClaims;

}

  getAllClaims(){
    this.dataSource=this.claims;
        //console.log(this.refunds)
    }
        //  this._http.get(this._loginUrl+mat+'/'+ponum).subscribe(resp => {console.log(resp)});


}