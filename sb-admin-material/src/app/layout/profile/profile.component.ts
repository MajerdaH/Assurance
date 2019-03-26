import { Component, OnInit } from '@angular/core';
import {Member} from './../../interfaces/member';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
enrolled: boolean;
member= new Member();
constructor(private router: Router) {
  this.enrolled=true;
  if(this.enrolled){
  this.member=this.initMember(this.member);
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
