import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormService } from 'src/app/form.service';
import { Form } from '../form';

declare var $ : any;

@Component({
  selector: 'app-forms-list',
  templateUrl: './forms-list.component.html',
  styleUrls: ['./forms-list.component.css']
})
export class FormsListComponent 
{
  forms : Form[] = [];
  activeForm : Form = new Form();

  constructor( 
    private service : FormService ,
    private router : Router,
  ) {}

  ngOnInit(): void 
  {
    this.refreshContent();
  }

  prepareModal( form : Form )
  {
    this.activeForm = new Form();

    event?.preventDefault();

    $( '#formModal' ).modal( 'show' );

    if ( form )
    {
      this.activeForm = form;
    }
  }

  confirmAction()
  {
    if ( this.activeForm.id )
    {
      this.service.editForm( this.activeForm ).subscribe( response => 
      {
        $( '#formModal' ).modal( 'hide' );
        $( '#toast' ).toast( 'show' );
      } );
    }

    else
    {
      this.service.addForm( this.activeForm ).subscribe( response => 
      {
        $( '#formModal' ).modal( 'hide' );
        $( '#toast' ).toast( 'show' );
        this.refreshContent();
      } );
    }
  }

  prepareDeleteModal( form : Form )
  {
    event?.preventDefault();

    $( '#deleteModal' ).modal( 'show' );

    this.activeForm = form;
  }

  deleteForm( formId : number )
  {
    this.service.deleteForm( formId ).subscribe( () => 
    {
      $( '#deleteModal' ).modal( 'hide' );
      $( '#toast' ).toast( 'show' );
      this.refreshContent();
    } );
  }

  refreshContent()
  {
    this.service.getForms()
                .subscribe( 
                response => this.forms = JSON.parse( JSON.stringify( response ) ).content,
                () => 
                {
                  this.router.navigate([ '' ])
                } );
  }

  cancelAction()
  {
    $( '#formModal' ).modal( 'hide' );
    $( '#deleteModal' ).modal( 'hide' );
  }
}
