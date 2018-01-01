import { PropertyModule } from './property/property.module';

import { AppComponent } from './app.component';

import { MaterialModule} from "./material.module";
import { PrimeModule } from "./prime.module";
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    PropertyModule,

    MaterialModule,
    PrimeModule,

    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
