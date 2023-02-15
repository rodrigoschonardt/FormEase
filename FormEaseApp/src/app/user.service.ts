import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService 
{
  apiUrl : string = environment.apiUrl;

  constructor(
    private http : HttpClient
  ) { }

  createUser( name : string, username : string, password : string )
  {
    const user =
    {
        "email" : username,
        "name" : name,
        "password" : password,
        "state" : 'ACTIVE'
    };
  
    const headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
    const options = { headers: headers };  
  
    return this.http.post( this.apiUrl + '/users', user, options );
  }
}
