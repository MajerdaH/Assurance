import { Component, OnInit } from '@angular/core';
import {Member} from './../../interfaces/member';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  
  private _InfoUrl ='http://localhost:8080/getMemberBy/';
  private _partnersUrl ='http://localhost:8080/getPartnersBy/';
  private _modifyUrl = 'http://localhost:8080/changeMemberInfos'; 
  private _pwdurl='http://localhost:8080/changePassword';
enrolled: boolean;
wife: boolean;
 child1: boolean;
  child2: boolean;
  child3: boolean;
  showModifyPwd:boolean;
  wifeInfos= new Member();
  child1Infos= new Member();
  child2Infos= new Member();
  child3Infos= new Member();
  showModifyInfos:boolean;
member= new Member();
partners: Array<Member> = [];
memberInfos:any;
bodyText:any;
modifyForm:any;
ponum:any; mat:any;
private body: any;
showPartners:boolean;
pwdFormGroup:any;
constructor(private router: Router, private _http: HttpClient) {
  this.showPartners=true;
  this.showModifyPwd=false;
  this.modifyForm =new FormGroup({
    rib: new FormControl(),
    phone: new FormControl(),
    address: new FormControl()
    });
    this.pwdFormGroup =new FormGroup({
      new: new FormControl(),
      old: new FormControl()
      });
  if (!localStorage.getItem('isLoggedin') || localStorage.getItem('isLoggedin')=='false') {
    this.router.navigate(['/login']);

   
}
this.initMember();
 //this.initPartners();
}


  initMember(){
    console.log(localStorage.getItem('ponum'));
    this.ponum=localStorage.getItem('ponum');
    console.log(localStorage.getItem('mat'));
    this.mat=localStorage.getItem('mat');
  
    this._http.get(this._InfoUrl+this.mat+'/'+this.ponum).subscribe(info =>{
      console.log(info)
      this.memberInfos=info;
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
  this.member.ponum=this.memberInfos.numP;
    });
    this.enrolled=true;
    this._http.get(this._partnersUrl+this.mat+'/'+this.ponum).subscribe(info =>{ console.log(info)
      let i:number;
     for(i=0;i<4;i++){
       if(info[i].type=='99'){this.wife=true;
        console.log(info[i].prpomt)
     this.wifeInfos.lastname=info[i].prpre;
     this.wifeInfos.birthdate=info[i].dateN;
     this.wifeInfos.matricule=info[i].prpomt;
      }
       if(info[i].type=='1'){this.child1=true;
        this.child1Infos.lastname=info[i].prpre;
        this.child1Infos.birthdate=info[i].dateN;
        this.child1Infos.matricule=info[i].prpomt;
      }
       if(info[i].type=='2'){this.child2=true;
        this.child2Infos.lastname=info[i].prpre;
        this.child2Infos.birthdate=info[i].dateN;
        this.child2Infos.matricule=info[i].prpomt;
      }
       if(info[i].type=='3'){this.child3=true;}
       this.child3Infos.lastname=info[i].prpre;
       this.child3Infos.birthdate=info[i].dateN;
       
       this.child3Infos.matricule=info[i].prpomt;
     }
     
    });


}
modifyInfos(){

  this.showModifyInfos=true;
  this.showPartners=false;
}

validateModifyInfos(){
  this.showPartners=true;
  console.log(this.modifyForm.value.rib);
    this.body= {};
    this.body['rib'] = this.modifyForm.value.rib;
    this.body['address'] = this.modifyForm.value.address;
    this.body['phone'] = this.modifyForm.value.phone;
    this.body['ponum'] =this.ponum;
    this.body['mat']=this.mat;
   
      this._http.post(this._modifyUrl,
       this.body,
         {
           headers: new HttpHeaders()
             .set('Content-Type', 'application/json')
         }
           ).subscribe(resp => {console.log(resp);
          if(resp=='OK'){
            this.member.rib= this.modifyForm.value.rib;
            this.member.phone=this.modifyForm.value.phone;
            this.member.address=this.modifyForm.value.address;
            this.showPartners=true;

            this.showModifyInfos=false;}
          })
}


showChangePwd(){
  console.log("pwd")
  this.showModifyPwd=true;
}
pwdBody:any;
changePassword(){
  this.pwdBody={}
  this.pwdBody['mat']=this.member.matricule;
  this.pwdBody['ponum']=this.member.ponum;
  this.pwdBody['newPassword']=this.pwdFormGroup.value.new;
  this.pwdBody['oldPassword']=this.pwdFormGroup.value.old;
  console.log(this.pwdFormGroup.value.new)
  this.showModifyPwd=false;
  this._http.post(this._pwdurl,
    this.pwdBody,
      {
        headers: new HttpHeaders()
          .set('Content-Type', 'application/json')
      }
        ).subscribe(resp => {console.log(resp);});
}

ngOnInit(){}

}
