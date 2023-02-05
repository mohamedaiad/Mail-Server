import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, NavigationEnd } from '@angular/router';
@Component({
  selector: 'app-trash',
  templateUrl: './trash.component.html',
  styleUrls: ['./trash.component.css']
})
export class TrashComponent implements OnInit {

  constructor(private http :HttpClient,private router: Router){
    let x = "show&trash&default"
    this.sendRequest(x)
  }
  ngOnInit(): void {
  }
  selectedMsgs:string=""
  json:any;
  length:any;
  page=1;
  trash = [
    {
      "id":0,
      "to":[{}],
      "from": "",
      "subject": "",
      "content": "",
      "date":"",
      "importane":0
    }
  ]
  sendRequest(x: string) {
    let origin = "http://localhost:8060/get/expression"
    
    if(location.port == "61606") {
      console.log("yes")
      origin = "http://localhost:8070/get/expression"
    }
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(origin, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
           this.json = response;
           this.trash = JSON.parse(this.json)
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  refresh(){
    window.location.reload()
    let x = "show&trash&default"
    this.sendRequest(x)
  }
  del(){
    this.selectedMsgs = this.selectedMsgs.substring(0, this.selectedMsgs.length - 1);
    let x = "deletetrash&" + this.selectedMsgs
    this.sendRequest(x)
    this.selectedMsgs=""
    this.refresh()
  }
  send(num: any) {
    this.selectedMsgs += num+'-'
  }
  
}