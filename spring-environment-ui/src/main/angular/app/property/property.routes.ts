import {PropertyComponent} from "./property.component";

import { Routes } from '@angular/router';

export const APP_ROUTES: Routes = [
  { path: 'properties', component: PropertyComponent },
  // { path: 'properties/:key', component: PropertyDetailComponent },
  // { path: 'properties/:key/edit', component: PropertiesEditorComponent },
  // // Wildcard route serves as fallback route and has to be
  // the last defined route in this list.
  { path: '**', redirectTo: 'properties' }
];
