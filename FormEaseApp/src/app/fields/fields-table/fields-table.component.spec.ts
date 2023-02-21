import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldsTableComponent } from './fields-table.component';

describe('FieldsTableComponent', () => {
  let component: FieldsTableComponent;
  let fixture: ComponentFixture<FieldsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FieldsTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FieldsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
