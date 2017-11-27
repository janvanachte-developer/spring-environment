import {Property} from '../property-table/property-table.component';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-resource',
  templateUrl: './property-resource.component.html',
  styleUrls: ['./property-resource.component.css']
})
export class PropertyResourceComponent implements OnInit {

  ngOnInit() {
  }

}
