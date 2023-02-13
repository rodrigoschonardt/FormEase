import { Component } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FormService } from 'src/app/form.service';
import { Form } from '../form';

@Component({
  selector: 'app-forms-list',
  templateUrl: './forms-list.component.html',
  styleUrls: ['./forms-list.component.css']
})
export class FormsListComponent 
{

  forms : Form[] = [];

  constructor( 
    private service : FormService ,
    private router : Router,
    private activatedRoute : ActivatedRoute
    ) {}

  ngOnInit(): void 
  {
    this.service.getForms()
                .subscribe( response => this.forms = JSON.parse( JSON.stringify( response ) ).content );
  }
}
