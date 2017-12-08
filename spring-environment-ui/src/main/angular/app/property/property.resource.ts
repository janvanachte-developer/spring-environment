import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import {Inject, Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_ENDPOINT} from "./property.tokens";
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

  // constructor(private httpClient: HttpClient, @Inject(API_ENDPOINT) private apiEndpoint) {
  //   this.getProperties();
  //   this.getData();
  // }

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

  // getProperties5(): Observable<Array<Property>> {
  //   return this.httpClient.get<Property[]>(`${this.apiEndpoint}/properties`)
  //     .pipe(map(data => data));
  //
  // }

}
