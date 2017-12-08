import { PropertyResource } from './property.resource';
import { PropertyComponent} from "./property.component"
import { PropertyListComponent } from './property-list/property-list.component';

import { APP_ROUTES } from './property.routes';
import { API_ENDPOINT } from './property.tokens';

import { environment } from '../environment';

import { MaterialModule} from "../material.module";
import { MatTableModule } from "@angular/material";

import { HttpModule } from '@angular/http';
//import { HttpClientModule } from '@angular/common/http';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    MaterialModule,
    MatTableModule,

    HttpModule,
    //HttpClientModule,
    CommonModule,
  ],
  declarations: [
    PropertyComponent,
    PropertyListComponent
  ],
  providers: [
    PropertyResource,
    { provide: API_ENDPOINT, useValue: environment.apiEndpoint }

  ],
  exports: [
    PropertyComponent
  ]
})
export class PropertyModule { }
