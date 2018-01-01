import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import {Inject, Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_ENDPOINT, URL} from "./property.tokens";
import {Observable} from "rxjs/Observable";
import {map} from "rxjs/operators";
import {Property} from "./property.model";
import {SelectItem} from 'primeng/components/common/api';

@Injectable()
export class PropertyService implements OnInit {

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

export interface SelectSubItem extends SelectItem {

  parent: string;
}

@Injectable()
export class PropertyServiceUsingObservables {

  constructor(private http: HttpClient, @Inject(API_ENDPOINT) private apiEndpoint) {
  }

  getProperty(key: string): Observable<Property> {
    let url = `${this.apiEndpoint}/${URL}/${key}`;
    let property$ = this.http.get<Property>(url).pipe(map(data => data));
    return property$;
  }

  getProperties(): Observable<Array<Property>> {
    const url = `${this.apiEndpoint}/${URL}`;
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


  getData() {
    const url = `${this.apiEndpoint}/${URL}`;

    return this.http.get(url)
      .toPromise()
      // .then(response  => <StatisticsReport[]> response.json().data)
      .then(response  => response as Property[])
      //.then(data => { return data; });
      .catch(this.handleError);
  }

  getSelectItems() {

    const selectItems:SelectItem[] = [
      {label:'Save in database', value:{id:1, name: 'Database', code: 'DB'}},
      {label:'Set in environment only', value:{id:2, name: 'Environment', code: 'ENV'}}
     ];

    return selectItems;
  }

  getSelectSubItems(selectItem?: SelectItem) {

    const selectSubItems:SelectSubItem[] = [
      {label:'Database with persistence', value:{id:1, name: 'Database with persistence', code: 'DB1'}, parent:'DB'},
      {label:'Database without persistence', value:{id:1, name: 'Database without persistence', code: 'DB2'}, parent:'DB'},
      {label:'Environment without persistence', value:{id:1, name: 'Environment without persistence', code: 'ENV1'}, parent:'ENV'}
    ]

    if ( ! selectItem ) {
      return selectSubItems;
    } else {
      return selectSubItems.filter((selectSubItem)=> selectSubItem.parent == selectItem.value.code);
    }
}

  private handleError(error: any): Promise<any> {
    console.error('Error', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
