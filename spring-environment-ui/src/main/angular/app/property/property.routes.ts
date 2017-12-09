import {PropertyEditorComponent} from "./property-editor-mat-card-with-observable/property-editor.component";

import {PropertyComponent} from "./property.component";

import { Routes } from '@angular/router';
import {PropertyListComponent} from "./property-list-material-with-observable/property-list.component";
import {PropertyTableComponent} from "./property-mat-table/property-mat-table.component";

export const APP_ROUTES: Routes = [
  { path: 'properties', component: PropertyTableComponent },
  { path: 'properties/:key', component: PropertyEditorComponent },
  // the last defined route in this list.
  { path: '**', redirectTo: 'properties' }
];
