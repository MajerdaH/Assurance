import { Component, OnInit } from '@angular/core';
import {Member} from './../../interfaces/member';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  
  private _InfoUrl ='http://localhost:8080/getMemberBy/';
enrolled: boolean;
member= new Member();
memberInfos:any;
constructor(private router: Router, private _http: HttpClient) {
      
  console.log(localStorage.getItem('ponum'));
  let ponum=localStorage.getItem('ponum');
  console.log(localStorage.getItem('mat'));
  let mat=localStorage.getItem('mat');

  this._http.get(this._InfoUrl+mat+'/'+ponum).subscribe(info =>{ this.memberInfos=info;
      this.member.lastname=this.memberInfos.nom;
      this.member.firstname=this.memberInfos.prenom;  
      this.member.matricule=this.memberInfos.mat;
      this.member.city=this.memberInfos.city;
this.member.birthdate="07/06/1993";
this.member.address=this.memberInfos.address;
this.member.phone=this.memberInfos.tel;
this.member.rib=this.memberInfos.rib;
this.member.country="Tunisie";
this.member.status=this.memberInfos.sit;
this.member.cp=this.memberInfos.cp;
this.member.joinDate=this.memberInfos.joinDate;
  });
  this.enrolled=true;
  if(this.enrolled){
  //this.member=this.initMember(this.member);
   // this.showMember=true;
  }else {
    this.router.navigate(['/enrollment']);
  }
}

  ngOnInit() {
  }


  initMember(member:Member){
    
member.firstname='Maissa';
member.lastname='Ferjani';
this.member.city="Tunis";
this.member.birthdate="07/06/1993";
this.member.address="street n abcd abdcef abcd abcdrf";
this.member.phone="+216 58 999 000";
this.member.rib="111 1111 1111 11";
this.member.country="Tunisie";
this.member.status="célibataire";
this.member.cp="4000";

return member; 
}

}
