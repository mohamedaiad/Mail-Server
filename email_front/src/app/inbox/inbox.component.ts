import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, NavigationEnd, Route } from '@angular/router';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  constructor(private http :HttpClient,private router:Router){
    let x = "show&inbox&default"
    this.sendRequest(x)
  }
  ngOnInit(): void {
  }
  Folder : string =""
  foldername :any
  folder:any;
  json:any;
  length:any;
  selectedMsgs:string=''
  page = 1;
  selected:string=''
  searchinput:string = "";
  searchvalue:any = "date";
  sortValue:any = "default";
  displayAlert:any = "None"
  inbox = [
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
           this.inbox = JSON.parse(this.json)
        }
          , (error) => {
            console.log(error);
          });
    }
  }  
  sendfile(x: string) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8060/get/expression", x,
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
  send(num: any) {
    this.selectedMsgs += num+'-'
    console.log(this.selectedMsgs);
  }
  refresh(){
    window.location.reload()
    let x = "show&inbox&default"
    this.sendRequest(x)
  }
  sort(event:any){
    this.sortValue=event.target.value 
  }
  sendSort(){
    let x = "show&inbox&"+this.sortValue
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
       let x = "show&inbox&search-"+ this.searchvalue + "-" + this.searchinput
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
  move(){
    this.selectedMsgs = this.selectedMsgs.substring(0, this.selectedMsgs.length - 1);
    let x = "move&" + this.selectedMsgs + "&inbox&" + this.selected
    console.log(x)
    this.sendRequest(x)
    this.refresh()
    this.selectedMsgs=''
  }

  del(){
    this.selectedMsgs = this.selectedMsgs.substring(0, this.selectedMsgs.length - 1);
    let x = "delete&" + this.selectedMsgs + "&inbox"
    this.sendRequest(x)
    this.refresh()
  }
  
}