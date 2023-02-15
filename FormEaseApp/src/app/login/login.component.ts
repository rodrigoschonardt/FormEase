import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';

declare var $ : any;

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
                        this.switchMode();
                        $( '#toast' ).toast( 'show' );
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
                        this.authService.setSession( response );
                        this.router.navigate(['/forms'])
                      }, errorResponse => 
                      {
                        console.log( 'erro' );
                      } )
    }
  }
}
