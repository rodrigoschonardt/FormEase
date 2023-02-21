import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Form } from './forms/form';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  apiUrl : string = environment.apiUrl + '/forms';

  constructor(
    private http : HttpClient
  ) { }

  getForms() : Observable<Form[]> 
  {
    return this.http.get<Form[]>( this.apiUrl + '/owner/' + localStorage.getItem( 'userId' ) );
  }

  getForm( id : number )
  {
    return this.http.get<Form>( this.apiUrl + "/" + id );
  }

  editForm( form : Form )
  {
    const formData =
    {
      'id' : form.id,
      'name' : form.name,
      'info' : form.info
    };

    const headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
    const options = { headers: headers };  

    return this.http.put( this.apiUrl, formData, options );
  }

  addForm( form : Form )
  {
    const formData =
    {
      'name' : form.name,
      'info' : form.info,
      'ownerId' : localStorage.getItem( 'userId' ),
    };

    const headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
    const options = { headers: headers };  

    return this.http.post( this.apiUrl, formData, options );
  }

  deleteForm( formId : number )
  {
    return this.http.delete( this.apiUrl + '/' + formId );
  }
}
