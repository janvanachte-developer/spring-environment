import { Property } from '../property.model';
import { PropertyResource } from '../property.resource';

import { MatTable, MatTableDataSource } from "@angular/material";

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {

  displayedColumns = ['key', 'value'];
  dataSource;

  propertySelected: Property;

  constructor( propertyResource: PropertyResource ) {
    propertyResource.getData().subscribe(data => {
      this.dataSource = new MatTableDataSource<Property>(data);
    })
  }

  ngOnInit() {

  }

  rowDblClick(property: Property, rowEvent) {
    console.log(property);
    this.propertySelected = property;
  }
}
