import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FieldService } from 'src/app/field.service';
import { FormService } from 'src/app/form.service';
import { Form } from 'src/app/forms/form';
import { Field } from '../field';
import { FieldTypes } from '../FieldTypes';

declare var $ : any;

@Component({
  selector: 'app-fields-table',
  templateUrl: './fields-table.component.html',
  styleUrls: ['./fields-table.component.css']
})
export class FieldsTableComponent 
{
  fields : Field[] = [];
  activeField : Field = new Field();
  formId : number;
  form : Form;
  fieldTypes : string[] = Object.keys( FieldTypes ).filter( t => isNaN( parseInt( t ) ) );
  

  constructor( 
    private fieldService : FieldService,
    private formService : FormService,
    private router : Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void 
  {
    this.refreshContent();
  }

  refreshContent()
  {
    this.route.params.subscribe( params => 
    {
      this.formId = params[ 'formId' ];      
    });

    this.formService.getForm( this.formId )
                    .subscribe(
                      response => this.form = JSON.parse( JSON.stringify( response ) )
                    );

    this.fieldService.getFields( this.formId )
                .subscribe( 
                response => this.fields = JSON.parse( JSON.stringify( response ) ).content,
                () => 
                {
                  this.router.navigate([ '' ])
                } );
  }

  prepareModal( field : Field )
  {
    this.activeField = new Field;

    event.preventDefault();

    $( '#fieldModal' ).modal( 'show' );

    if ( field )
    {
      this.activeField = field
    }
  }

  prepareDeleteModal( field : Field )
  {
    event?.preventDefault();

    $( '#deleteModal' ).modal( 'show' );

    this.activeField = field;
  }

  openDropdown()
  {
    $( '#typeDropdown' ).dropdown( 'toggle' );
  }

  changeType( type : string )
  {
    event.preventDefault();

    this.activeField.type = type;
  }

  changeRequired()
  {
    this.activeField.required = !this.activeField.required;
  }

  confirmAction()
  {
    if ( this.activeField.id )
    {
      this.fieldService.editField( this.activeField ).subscribe( () => 
      {
        $( '#fieldModal' ).modal( 'hide' );
        $( '#toast' ).toast( 'show' );
        this.refreshContent();
      } );
    }

    else
    {
      this.fieldService.addField( this.activeField, this.formId ).subscribe( () => 
      {
        $( '#fieldModal' ).modal( 'hide' );
        $( '#toast' ).toast( 'show' );
        this.refreshContent();
      } );
    }
  }

  cancelAction()
  {
    $( '#fieldModal' ).modal( 'hide' );
    $( '#deleteModal' ).modal( 'hide' );
  }

  deleteField( fieldId : number )
  {
    this.fieldService.deleteField( fieldId ).subscribe( () => 
    {
      $( '#deleteModal' ).modal( 'hide' );
      $( '#toast' ).toast( 'show' );
      this.refreshContent();
    } );
  }
}
