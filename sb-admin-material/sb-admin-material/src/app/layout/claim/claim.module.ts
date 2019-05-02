import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClaimComponent } from './claim.component';
import { ClaimRoutingModule } from './claim-routing-module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule, MatIconModule, MatTableModule, MatCardModule } from '@angular/material';
import { MatGridListModule } from '@angular/material/grid-list';
import { FileSelectDirective } from 'ng2-file-upload';

import { StatModule } from '../../shared/modules/stat/stat.module';
import { FormsModule as FormModule, ReactiveFormsModule } from '@angular/forms';
import {
    MatAutocompleteModule, MatCheckboxModule, MatDatepickerModule,
    MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
    MatSliderModule, MatSlideToggleModule
} from '@angular/material';

@NgModule({
  declarations: [ClaimComponent, FileSelectDirective],
  imports: [ CommonModule,
    MatGridListModule,
    StatModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatAutocompleteModule,
    FormModule,
    ReactiveFormsModule,
    MatSlideToggleModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatCheckboxModule,
    MatRadioModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatSelectModule,
    MatSliderModule,
    FlexLayoutModule.withConfig({addFlexToParent: false})
    , ClaimRoutingModule
  ]
})
export class ClaimModule { }
