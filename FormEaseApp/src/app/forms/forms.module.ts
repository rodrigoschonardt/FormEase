import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsListComponent } from './forms-list/forms-list.component';


@NgModule({
  declarations: 
  [
    FormsListComponent
  ],
  imports: [
    CommonModule
  ],
  exports :
  [
    FormsListComponent
  ]
})
export class FormModule { }
