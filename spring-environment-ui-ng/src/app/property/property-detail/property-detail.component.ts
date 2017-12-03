import { Component, OnInit } from '@angular/core';
import {Property} from "../property-table/property-table.component";
import {PropertyTableComponent} from "../property-table/property-table.component"

@Component({
  selector: 'app-property-detail',
  templateUrl: './property-detail.component.html',
  styleUrls: ['./property-detail.component.css']
})
export class PropertyDetailComponent implements OnInit {

  property: Property;

  constructor() {
    this.property  = { key: { stringValue: 'prop1key'} , value: { stringValue: 'value'} , changeable: false};
  }

  ngOnInit() {
  }

}
