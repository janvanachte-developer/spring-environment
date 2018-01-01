import { Property } from '../property.model';
import { PropertyService } from '../property.service';

import { MatTable, MatTableDataSource } from "@angular/material";

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-mat-table',
  templateUrl: './property-mat-table.component.html',
  styleUrls: ['./property-mat-table.component.css']
})
export class PropertyTableComponent implements OnInit {

  title = 'Properties (Material Table)  ';

  displayedColumns = ['key', 'value'];
  dataSource;

  propertySelected: Property;

  constructor( propertyResource: PropertyService ) {
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
