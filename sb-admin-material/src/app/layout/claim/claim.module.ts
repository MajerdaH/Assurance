import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClaimComponent } from './claim.component';
import { ClaimRoutingModule } from './claim-routing-module';

@NgModule({
  declarations: [ClaimComponent],
  imports: [
    CommonModule, ClaimRoutingModule
  ]
})
export class ClaimModule { }
