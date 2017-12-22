import { PropertyResource } from './property.resource';
import {PropertyResourceUsingObservables} from "./property.resource";
import { PropertyComponent} from "./property.component"
import { PropertyTableComponent } from './property-mat-table/property-mat-table.component';
import { PropertyListComponent as PropertyListComponent_Material_Observables } from "./property-list-material-with-observable/property-list.component"
import { PropertyEditorComponent } from "./property-editor-mat-card-with-observable/property-editor.component"

import { APP_ROUTES } from './property.routes';
import { API_ENDPOINT } from './property.tokens';

import { environment } from '../environment';

import { MaterialModule} from "../material.module";
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule} from "@angular/platform-browser/animations";

import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";

@NgModule({
  imports: [

    RouterModule.forRoot(APP_ROUTES),

    MaterialModule,
    FormsModule,
    BrowserAnimationsModule,

    HttpModule,
    HttpClientModule,
    CommonModule,
  ],
  declarations: [
    PropertyComponent,
    PropertyTableComponent,
    PropertyListComponent_Material_Observables,

    PropertyEditorComponent
  ],
  providers: [
    PropertyResource, PropertyResourceUsingObservables,
    { provide: API_ENDPOINT, useValue: environment.apiEndpoint }

  ],
  exports: [
    PropertyComponent
  ]
})
export class PropertyModule { }
