import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
    constructor(private router: Router) {
       /* if(localStorage.getItem('register')=='true'){
            this.router.navigate(['/register']);
        }
        if ((!localStorage.getItem('isLoggedin') || localStorage.getItem('isLoggedin')=='false')&&
         localStorage.getItem('register')=='false') {
            this.router.navigate(['/login']);*/
        
    }

    ngOnInit() {}
}
