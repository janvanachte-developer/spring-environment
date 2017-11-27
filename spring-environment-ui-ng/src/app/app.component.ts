import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';


import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  private url = 'http://localhost:8080/cxfservlet/properties/';

  data: any = {};

  constructor(private http: Http) {
    this.getProperties();
    this.getData();
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
