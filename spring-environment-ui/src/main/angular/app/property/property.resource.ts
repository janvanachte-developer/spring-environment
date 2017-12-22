import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import {Inject, Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_ENDPOINT, URL} from "./property.tokens";
import {Observable} from "rxjs/Observable";
import {map} from "rxjs/operators";
import {Property} from "./property.model";

@Injectable()
export class PropertyResource implements OnInit {

  private url = 'http://localhost:8080/cxfservlet/properties/';

  data: any = {};

  constructor(private http: Http) {
    this.getProperties();
    this.getData();
  }

  ngOnInit() {
  }

  getProperties() {
    this.getData().subscribe(data => {
      console.log(data);
      this.data = data;
    })
  }

  public getData() {
    return this.http.get(this.url).map((response: Response) => response.json());
  }
}

@Injectable()
export class PropertyResourceUsingObservables {

  constructor(private http: HttpClient, @Inject(API_ENDPOINT) private apiEndpoint) {
  }

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
