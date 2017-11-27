import { PropertyModule} from './property/property.module';
import { AppComponent } from './app.component';

import { HttpModule} from "@angular/http";

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    PropertyModule,

    HttpModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
