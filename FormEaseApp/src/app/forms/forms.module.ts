import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsListComponent } from './forms-list/forms-list.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: 
  [
    FormsListComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports :
  [
    FormsListComponent
  ]
})
export class FormModule { }
