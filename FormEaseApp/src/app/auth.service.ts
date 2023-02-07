import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService 
{
  apiUrl : string = environment.apiUrl;

  constructor(
    private http : HttpClient
  ) { }

  authenticate( username : string, password : string )
  {
    const user = JSON.stringify(
    {
      "email" : username,
      "password" : password
    } );

    const headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
    const options = { headers: headers };  

    return this.http.post( this.apiUrl + '/auth', user, options );
  }
}
