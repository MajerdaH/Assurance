import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';



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
    refunds:any;
    places: Array<any> = [];
    matricule:string;
    ponum:number;
    displayedColumns = ['bull','careDate','setDate','cPolicy', 'name', 'progress', 'color','action'];
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

    constructor(private _http: HttpClient, private router: Router) {

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

        this._http.get(this._loginUrl+mat+'/'+ponum).subscribe(resp => this.refunds=resp); 
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
    console.log(localStorage.getItem('ponum'));
    let ponum=localStorage.getItem('ponum');
    console.log(localStorage.getItem('mat'));
    let mat=localStorage.getItem('mat');
    this._http.get(this._BullDetailsUrl+mat+'/'+ponum+'/'+wbul).subscribe(resp => this.refundsDetails=resp); 
    this.getAllRefunds();
}
    }

