import { Property } from '../property.model';
import { PropertyResource } from '../property.resource';

import { PropertyResourceUsingObservables } from "../property.resource";
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-editor-mat-card',
  templateUrl: './property-editor.component.html',
  styleUrls: ['./property-editor.component.css']
})
export class PropertyEditorComponent implements OnInit {

  title = 'Property (Material Card)';

  // we need to initialize since we can't use ?. operator with ngModel
  property: Property = new Property;

  constructor(private propertyResource: PropertyResourceUsingObservables,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit() {
    let key = this.route.snapshot.paramMap.get('key');
    console.log(`Property key ${key}`);

    this.propertyResource.getProperty(this.route.snapshot.paramMap.get('key'))
      .subscribe(property => {
          console.log(`Property ${property}`);
          this.property = property;
        });
  }

  save(property: Property) {
    this.propertyResource.updateProperty(property)
      .subscribe(() => this.goToOverview(property));
  }

  cancel(property: Property) {
    this.goToOverview(property);
  }

  ok(property: Property) {
    this.goToOverview(property);
  }


  private goToDetails(property: Property) {
    this.router.navigate(['/properties', property.key ]);
  }

  private goToOverview(property: Property) {
    this.router.navigate(['/properties']);
  }
}
