import { Property } from '../property.model';
import { PropertyResource } from '../property.resource';

import { MatTable, MatTableDataSource } from "@angular/material";

import { Component, OnInit } from '@angular/core';
import {PropertyService} from "../property.service";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-property-list-material-with-observable',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {

  title = 'Properties (Material List with Observable)';
  columnHeader_key = 'Property';
  columnHeader_value = 'Value';

  displayedColumns = ['key', 'value'];
  dataSource;
  private properties$: Observable<Array<Property>>;

  constructor( private propertyResource: PropertyService ) {
  }

  ngOnInit() {
    this.properties$ = this.propertyResource.getProperties();
    console.log(`${this.title} `);
    this.properties$.subscribe(data => console.log(data));
  }

  trackByPropertyKey(index, property) {
    return property.key;
  }
}
