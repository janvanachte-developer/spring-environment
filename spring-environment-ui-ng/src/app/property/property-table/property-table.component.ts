import { Component, OnInit } from '@angular/core';
import {AppComponent} from "../../app.component";
import {getAppFromConfig} from "@angular/cli/utilities/app-utils";

@Component({
  selector: 'app-property-table',
  templateUrl: './property-table.component.html',
  styleUrls: ['./property-table.component.css']
})
export class PropertyTableComponent implements OnInit {

  data: any = null;

  constructor( appComponent: AppComponent) {
    appComponent.getData().subscribe(data => {
      this.data = data;
    });
  }

  ngOnInit() {
  }

}

export interface Property {
  key: string;
  value: string;
  changeable: boolean;
}
