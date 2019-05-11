import { Component, OnInit } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';

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
    public uploader: FileUploader = new FileUploader({url: URL, itemAlias: 'photo'});
    constructor() {}

    ngOnInit() {
        this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
        this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
             console.log('ImageUpload:uploaded:', item, status, response);
             alert('File uploaded successfully');
         };
    }


    
}
