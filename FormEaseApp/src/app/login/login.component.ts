import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent 
{
  name : string;
  username : string;
  password : string;
  isSigningUp : boolean;

  constructor(
    private router : Router,
    private authService : AuthService,
    private userService : UserService
  ) { }

  switchMode()
  {
    this.isSigningUp = !this.isSigningUp;
  }

  submit()
  {    
    if ( this.isSigningUp )
    {
      this.userService.createUser( this.name, this.username, this.password )
                      .subscribe( response => 
                      {
                        console.log( response );
                      }, errorResponse => 
                      {
                        console.log( 'erro' );
                      } )
    }

    else
    {
      this.authService.authenticate( this.username, this.password )
                      .subscribe( response => 
                      {
                        console.log( 'logado' );
                      }, errorResponse => 
                      {
                        console.log( 'erro' );
                      } )
    }
  }
}
