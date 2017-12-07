import { PropertyModule } from './property/property.module';

import { AppComponent } from './app.component';

import {Ng2TableViewModule} from 'NG2TableView';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    PropertyModule,

    Ng2TableViewModule,

    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
