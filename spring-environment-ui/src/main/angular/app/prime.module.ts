import {NgModule} from "@angular/core";
import {DataTableModule, DialogModule, DropdownModule, ToolbarModule} from "primeng/primeng";

@NgModule({
  exports: [
    DataTableModule,
    DialogModule,
    DropdownModule,
    ToolbarModule
  ]
})
export class PrimeModule{}
