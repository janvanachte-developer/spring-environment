import {PropertyResourceComponent} from '../property-resource/property-resource.component';
import { DataTableResource } from 'angular-4-data-table/src/index';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-table',
  templateUrl: './property-table.component.html',
  styleUrls: ['./property-table.component.css']
})
export class PropertyTableComponent implements OnInit {

  edited = false;

  propertyData: any = [];
  propertyResource = new DataTableResource(this.propertyData);
  itemCount = 0;
  propertySelected: Property;

  constructor( propertyResource: PropertyResourceComponent) {
    propertyResource.getData().subscribe(propertyData => {
      this.propertyData = propertyData;

      this.propertyResource.count().then(count => this.itemCount = count);
    });
  }

  rowClick(property: Property, rowEvent) {

    this.propertySelected = property;

    console.log('Clicked: ' + property.key.stringValue);
    this.edited = true;
  }

  rowDoubleClick(rowEvent) {
    alert('Double clicked: ' + rowEvent.row.item.name);

  }

  rowTooltip(item) { return item.jobTitle; }

  ngOnInit() {
  }
}

export interface Property {
  key: Key;
  value: Value;
  changeable: boolean;
}

export interface Key {
  stringValue: string;
}

export interface Value {
  stringValue: string;
}
