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
    let origin = "http://localhost:8060/get/expression"
    
    if(location.port == "61606") {
      origin = "http://localhost:8070/get/expression"
    }
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(origin, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.json = response;
          this.show = JSON.parse(this.json)
          console.log(this.show)
          this.final = this.show.contacts;

        }
          , (error) => {
            console.log(error);
          });
    }
  }
  send(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8060/get/expression", x,
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
    let x = "addcontact&" + this.email
    this.send(x)
    window.location.reload()
  }
  delete(item:any){
     console.log(item)
     let x ="removecontact&"+item
     this.send(x)
     window.location.reload()
  }
}