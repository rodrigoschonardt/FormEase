import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FieldsTableComponent } from './fields-table/fields-table.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: 
  [
    FieldsTableComponent
  ],
  imports: 
  [
    CommonModule,
    FormsModule
  ],
  exports: 
  [
    FieldsTableComponent
  ]
})
export class FieldsModule 
{
}
