import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Form } from './forms/form';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  apiUrl : string = environment.apiUrl;

  constructor(
    private http : HttpClient
  ) { }

  getForms() : Observable<Form[]> 
  {
    return this.http.get<Form[]>( this.apiUrl + '/forms' );
  }
}
