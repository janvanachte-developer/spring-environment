import { Property } from '../property.model';
import {PropertyService, SelectSubItem} from '../property.service';

import { Component, OnInit } from '@angular/core';
import {PropertyServiceUsingObservables} from "../property.service";
import {Observable} from "rxjs/Observable";
import {SelectItem} from "primeng/primeng";

const title = 'Properties (Prime DataTable with Observable)';

@Component({
  selector: 'app-property-list-prime-with-observable',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {

  // DataTable
  private rows: Property[];
  private row = new Property();
  private selection: Property;

  // Dropdown
  private options: SelectItem[];
  private subOptions: SelectSubItem[];


  displayDialog: boolean;
  isNew: boolean;

  constructor(private service: PropertyServiceUsingObservables ) {}

  ngOnInit() {
    this.populateRows();
    this.populateDropdowns();
  }

  private populateRows() {
    this.service.getData().then(data => this.rows = data);
  }

  private populateDropdowns() {
    this.options = this.service.getSelectItems();
    this.subOptions = this.service.getSelectSubItems();
  }

  private onDropdownSelect(event) {

  }

  private onDropdownL2Select(event) {

  }


  save() {
    if(this.isNew)
      this.rows.push(this.row);
    else
      this.rows[this.rows.indexOf(this.selection)] = this.row;

    this.row = null;
    this.displayDialog = false;
  }

  delete() {
    // this.reports.splice(index, 1);
    // this.property = null;
    // this.displayDialog = false;
  }

  onRowSelect(event) {
    this.isNew = false;
    this.row = this.cloneCar(event.data);
    this.displayDialog = true;
  }

  cloneCar(c: Property): Property {
    let property = new Property();
    for(let prop in c) {
      property[prop] = c[prop];
    }
    return this.row;
  }
}
