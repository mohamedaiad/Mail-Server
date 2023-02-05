import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Location } from '@angular/common';
import { Router, NavigationEnd, Route } from '@angular/router';
@Component({
  selector: 'app-sent',
  templateUrl: './sent.component.html',
  styleUrls: ['./sent.component.css']
})
export class SentComponent implements OnInit {

  constructor(private http :HttpClient , private router:Router){
    let x = "show&sent&default"
    this.sendRequest(x)
  }
  ngOnInit(): void {
  }
  selectedMsgs:string=""
  page = 1;
  json:any;
  Folder : string =""
  foldername :any
  folder:any;
  selected:string=''
  sent = [
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
      origin = "http://localhost:8070/get/expression"
    }
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(origin, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
           this.json = response;
           this.sent = JSON.parse(this.json)
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  sendfile(x: string) {
    let origin = "http://localhost:8060/get/expression"
    
    if(location.port == "61606") {
      origin = "http://localhost:8070/get/expression"
    }
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(origin, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.Folder = response
          console.log(this.Folder)
          this.foldername =  this.Folder.split(",")
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  refresh(){
    window.location.reload()
    let x = "show&sent&default"
    this.sendRequest(x)
  }
  searchinput:string = "";
  searchvalue:any = "date";
  sortValue:any = "default";
  displayAlert:any = "None"
  sort(event:any){
    this.sortValue=event.target.value 
  }
  sendSort(){
    let x = "show&sent&"+this.sortValue
    this.sendRequest(x)
  }
  searchInput(event:any){
    this.searchinput=event.target.value
    if( this.searchinput==""){
      this.sendSort
    }
  }
  searchValue(event:any){
    console.log(event.target.value);
    this.searchvalue=event.target.value 
  }
  sendSearch(){
     if(this.searchinput!=""){
       let x = "show&sent&search-"+ this.searchvalue + "-" + this.searchinput
       console.log(x)
      this.sendRequest(x) 
      this.displayAlert= "None"   
     }
     else{
        this.displayAlert= "block"
     }
  }
  showFolders() {
    let x = "showfolders"
    this.sendfile(x)
  }  
  findName(event:any){
    this.selected=event.target.value    
  }
  send(num: any) {
    if(this.selectedMsgs.includes(num))
    {
      var value = num + "-"
      this.selectedMsgs = this.selectedMsgs.replace(value,'')
      console.log(value)
      console.log(this.selectedMsgs)
    }
    else
    {
      this.selectedMsgs += num+'-'
      console.log(num)
      console.log(this.selectedMsgs)
    }
  }
  move(){
    this.selectedMsgs = this.selectedMsgs.substring(0, this.selectedMsgs.length - 1);
    let x = "move&" + this.selectedMsgs + "&sent&" + this.selected
    this.sendRequest(x)
    this.refresh()
    this.selectedMsgs=''
  }
  del(){
    this.selectedMsgs = this.selectedMsgs.substring(0, this.selectedMsgs.length - 1);
    let x = "delete&" + this.selectedMsgs + "&sent"
    this.sendRequest(x)
    this.selectedMsgs=""
    this.refresh()
  }
}