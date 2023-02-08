import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MapType } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {

  constructor(private http: HttpClient) {
    let x = "showcontact"
    this.sendRequest(x)
  }

  ngOnInit(): void {
  }
  json: any
  email: any;
  answer: any;
  show: any;
  final: string[] = [];
  sendRequest(x: string) {
    let origin = "http://localhost:8070/Email/show/contact"

    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(origin, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.json = response;
          this.show = JSON.parse(this.json)
          this.final = this.show.contacts;
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  URL : string = ""
  send(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(this.URL, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.answer = response;
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  find() {
    let x = this.email
    this.URL = "http://localhost:8070/Email/add/contact"
    this.send(x)
    window.location.reload()
  }
  delete(item:any){
     let x = item
     this.URL = "http://localhost:8070/Email/remove/contact"
     this.send(x)
     window.location.reload()
  }
}
