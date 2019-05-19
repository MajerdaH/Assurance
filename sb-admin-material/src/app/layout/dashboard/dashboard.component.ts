import { Component, OnInit, Inject } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { DialogOverviewExampleDialogComponent } from 'src/app/dialog-overview-example-dialog/dialog-overview-example-dialog.component';

export interface DialogData {
    animal: string;
    name: string;
  }

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
    private _loginUrl = 'http://localhost:8080/getMemberBullsBy/'; 
    private _InfoUrl ='http://localhost:8080/getMemberBy/';
    private _BullDetailsUrl= 'http://localhost:8080/getMemberRefundsBy/';
    refundsDetails:any;
    showAllBulls:any;
    refunds:any;
    places: Array<any> = [];
    matricule:string;
    ponum:number;
    displayedColumns = ['bull','setDate', 'name', 'progress', 'color','status','action'];
    dataSource: MatTableDataSource<any>;
    memberInfos:any;
    name;
    showDetailsBull:boolean;
    bulln:string;

    applyFilter(filterValue: string) {
        filterValue = filterValue.trim(); // Remove whitespace
        filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
        this.dataSource.filter = filterValue;
    }


    searchBull(bulln:string){console.log(bulln);
      }

    constructor(private _http: HttpClient, private router: Router, public dialog: MatDialog) {
        this.showAllBulls=true;
        this.showDetailsBull=false;
        console.log(localStorage.getItem('isLoggedin'));
        this.showDetailsBull=false;
        if (!localStorage.getItem('isLoggedin') || localStorage.getItem('isLoggedin')=='false') {
            this.router.navigate(['/login']);
        }else {
        console.log(localStorage.getItem('ponum'));
        let ponum=localStorage.getItem('ponum');
        console.log(localStorage.getItem('mat'));
        let mat=localStorage.getItem('mat');

        this._http.get(this._InfoUrl+mat+'/'+ponum).subscribe(info =>{ this.memberInfos=info;
            this.name=this.memberInfos.nom+ " "+this.memberInfos.prenom;
            localStorage.setItem('memberName', this.name);
        }); 

        this._http.get(this._loginUrl+mat+'/'+ponum).subscribe(resp => {this.refunds=resp,
            console.log(resp)}); 
            this.getAllRefunds();

//console.log(this.refunds)
    }
}

    ngOnInit() {}


    getAllRefunds(){
this.dataSource=this.refunds;
    //console.log(this.refunds)
}

showDetailsBulletin(wbul:number){
    this.showDetailsBull=true
    this.showAllBulls=false;
    console.log(localStorage.getItem('ponum'));
    let ponum=localStorage.getItem('ponum');
    console.log(localStorage.getItem('mat'));
    let mat=localStorage.getItem('mat');
    this._http.get(this._BullDetailsUrl+mat+'/'+ponum+'/'+wbul).subscribe(resp => 
        {this.refundsDetails=resp; console.log(resp);});
       // this.getAllRefunds();
}
showBack(){
    this.showAllBulls=true;
    this.showDetailsBull=false;
}
/*openDialog(){
    const dialogRef = this.dialog.open(DialogOverviewExampleDialogComponent, {
      width: '250px',
      data: {name: "hejer", animal: "mej"}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }*/
    }
