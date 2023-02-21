import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { FieldsTableComponent } from './fields/fields-table/fields-table.component';
import { FormsListComponent } from './forms/forms-list/forms-list.component';
import { LayoutComponent } from './layout/layout.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = 
[
  { path : '', component : LoginComponent },
  { path : '', component : LayoutComponent, children : [
    { path : 'forms', component : FormsListComponent, canActivate : [AuthGuard] },
    { path : 'fields/:formId', component : FieldsTableComponent, canActivate : [AuthGuard] }
  ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
