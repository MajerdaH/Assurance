import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import {  FileUploader, FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';
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
  
displayedColumns = ['postDate', 'type', 'title', 'description', 'status'];
dataSource = new MatTableDataSource(ELEMENT_DATA);

showAddSuggestion:boolean;
showClaims:boolean;
public uploader: FileUploader = new FileUploader({url: URL, itemAlias: 'photo'});

  constructor() {
    }

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
}