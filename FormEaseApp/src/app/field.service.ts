import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Field } from './fields/field';

@Injectable({
  providedIn: 'root'
})
export class FieldService {

  apiUrl : string = environment.apiUrl + '/fields';

  constructor(
    private http : HttpClient
  ) { }

  getFields( formId : number ) : Observable<Field[]> 
  {
    return this.http.get<Field[]>( this.apiUrl + '/form/' + formId );
  }

  editField( field : Field )
  {
    const fieldData = 
    {
      'id' : field.id,
      'label' : field.label,
      'required' : field.required,
      'type' : field.type
    }

    const headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
    const options = { headers: headers };  

    return this.http.put( this.apiUrl, fieldData, options );
  }

  addField( field : Field, formId : number )
  {
    const fieldData = 
    {
      'label' : field.label,
      'required' : field.required,
      'type' : field.type,
      'formId' : formId
    }

    const headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
    const options = { headers: headers };  

    return this.http.post( this.apiUrl, fieldData, options );
  }

  deleteField( fieldId : number )
  {
    return this.http.delete( this.apiUrl + '/' + fieldId );
  }
}
