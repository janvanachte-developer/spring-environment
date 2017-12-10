import { Property } from './property.model';
import { API_ENDPOINT } from './property.tokens';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { Injectable, Inject } from '@angular/core';
import { map } from 'rxjs/operators';

const URL = 'properties';

@Injectable()
export class PropertyResourceUsingObservables {

  constructor(private http: HttpClient, @Inject(API_ENDPOINT) private apiEndpoint) {}

  getProperty(key: string): Observable<Property> {
    let url = `${this.apiEndpoint}/${URL}/${key}`;
    let property$ = this.http.get<Property>(url).pipe(map(data => data));
    return property$;
  }

  getProperties(): Observable<Array<Property>> {
    let url = `${this.apiEndpoint}/${URL}`;
    let properties$ = this.http.get<Array<Property>>(url).pipe(map(data => data));
    return properties$;
  }

  updateProperty(property: Property): Observable<Property> {
    let url = `${this.apiEndpoint}/${URL}/${property.key}`;
    let property$ = this.http.put<Property>(url, property).pipe(map(data => data));
    return property$;
  }

  addProperty(property: Property) {
    return this.http.post<Property>(`${this.apiEndpoint}/${URL}/`, property)
      .pipe(map(data => data));
  }
}
