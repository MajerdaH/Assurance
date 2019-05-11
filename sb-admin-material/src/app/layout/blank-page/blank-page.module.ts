import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlankPageRoutingModule } from './blank-page-routing.module';
import { BlankPageComponent } from './blank-page.component';
import { MatGridListModule, MatCardModule, MatButtonModule, MatIconModule, MatAutocompleteModule, MatSlideToggleModule, MatInputModule, MatFormFieldModule, MatCheckboxModule, MatRadioModule, MatDatepickerModule, MatNativeDateModule, MatSelectModule, MatSliderModule } from '@angular/material';
import { StatModule } from 'src/app/shared/modules/stat/stat.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

import { FormsModule as FormModule } from '@angular/forms';
import { FileSelectDirective } from 'ng2-file-upload';

@NgModule({
    imports: [CommonModule, BlankPageRoutingModule,
        CommonModule,
        MatGridListModule,
        StatModule,
        MatCardModule,
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
    ],
    declarations: [BlankPageComponent, FileSelectDirective]
})
export class BlankPageModule {}
