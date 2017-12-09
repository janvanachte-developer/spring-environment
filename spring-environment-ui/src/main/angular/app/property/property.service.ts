import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators';
import { API_ENDPOINT } from './property.tokens';

import { Property } from './property.model';

interface PropertyResponse  { item  : Property    }
interface PropertiesResponse { items : Property[]  }


@Injectable()
export class PropertyService {

  constructor(private http: HttpClient, @Inject(API_ENDPOINT) private apiEndpoint) {}

  getProperty(key: string): Observable<Property> {
    let url = `${this.apiEndpoint}/properties/${key}`;
    console.log(`getProperty ${key} from URL ${url}`);

    let property = this.http.get<PropertyResponse>(url)
        .pipe(map(data => data));

    return property;
  }

  getProperties(): Observable<Array<Property>> {
    console.log("getProperties");

    return this.http.get<PropertiesResponse>(`${this.apiEndpoint}/properties`)
        .pipe(map(data => data.items));
  }

  updateProperty(property: Property): Observable<Property> {
    return this.http.put<PropertyResponse>(`${this.apiEndpoint}/properties/${property.key}`, property)
        .pipe(map(data => data.item));
  }

  addProperty(property: Property) {
    return this.http.post<PropertyResponse>(`${this.apiEndpoint}/properties/`, property)
      .pipe(map(data => data.item));
  }
}
