import {Directive, OnDestroy} from '@angular/core';
//import {AuthService} from '../services/auth.service';
import { Router } from '@angular/router';

@Directive({
    selector: '[protected]'
})
export class ProtectedDirective {

    constructor(private router:Router, private location:Location) {
        if (!localStorage.getItem('isLoggedin')) {
            this.router.navigate(['/login']);
        }

    }

}