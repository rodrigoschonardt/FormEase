import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import * as moment from 'moment';
import jwt_decode from 'jwt-decode';

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
    const user = 
    {
      "email" : username,
      "password" : password
    };

    const headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
    const options = { headers: headers };  

    return this.http.post( this.apiUrl + '/auth', user, options );
  }

  setSession( response : object ) 
  {
    const token = JSON.parse( JSON.stringify(response) );
    const decodedToken = JSON.parse( JSON.stringify( jwt_decode( token.token ) ) )

    const expiresAt = moment().add( decodedToken.exp );

    localStorage.setItem( 'token', token.token );
    localStorage.setItem( 'expiration', JSON.stringify( expiresAt.valueOf() ) );
    localStorage.setItem( 'userId', decodedToken.sub )
  }          

  logout() 
  {
    localStorage.removeItem( 'token' );
    localStorage.removeItem( 'expiration' );
    localStorage.removeItem( 'userId' );
  }

  public isLoggedIn() 
  {
    return moment().isBefore( this.getExpiration() );
  }

  isLoggedOut() 
  {
    return !this.isLoggedIn();
  }

  getExpiration() 
  {
    const expiration = localStorage.getItem( 'expiration' );

    return expiration != null ? moment( JSON.parse( expiration ) ) : moment();
  }  
}
