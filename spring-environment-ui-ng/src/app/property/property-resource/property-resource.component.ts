import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-resource',
  templateUrl: './property-resource.component.html',
  styleUrls: ['./property-resource.component.css']
})
export class PropertyResourceComponent implements OnInit {

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
