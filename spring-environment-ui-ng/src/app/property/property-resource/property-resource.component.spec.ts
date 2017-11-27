import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyResourceComponent } from './property-resource.component';

describe('PropertyResourceComponent', () => {
  let component: PropertyResourceComponent;
  let fixture: ComponentFixture<PropertyResourceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropertyResourceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyResourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
