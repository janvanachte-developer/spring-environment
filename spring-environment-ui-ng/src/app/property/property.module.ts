import { PropertyTableComponent } from './property-table/property-table.component';
import { PropertyDetailComponent } from './property-detail/property-detail.component';

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PropertyViewComponent } from './property-view/property-view.component';
import { PropertyResourceComponent } from './property-resource/property-resource.component';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    PropertyResourceComponent
  ],
  exports: [
    PropertyViewComponent
  ],
  declarations: [PropertyTableComponent, PropertyDetailComponent, PropertyViewComponent, PropertyResourceComponent]
})
export class PropertyModule { }
