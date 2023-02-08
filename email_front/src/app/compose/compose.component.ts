import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ValueTransformer } from '@angular/compiler/src/util';
@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }
  subject: string = ''
  message: string = ''
  attachment: string = ''
  expression: string = ''
  display: string = 'None'
  importance: string = '2'
  list:any [] = ["1"]
  toList:string[]=[]
  answer:string=''
  url: any
  msg = {
    "to": this.toList,
    "subject": this.subject,
    "content": this.message,
    "date": "",
    "importance": this.importance
  }
  URL: string = ""
  sendRequest(x: any) {

    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(this.URL, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
           this.answer = response;
           console.log(this.answer)
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  readUrl(event: any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.onload = (event: ProgressEvent) => {
        this.url = (<FileReader>event.target).result;
      }
      reader.readAsDataURL(event.target.files[0]);
      console.log(reader.result)
    }
  }
  getattachment(){
    this.sendRequest("addattachment")
  }
  seenattach(){
    window.open(this.answer,"_blank")
  }
  send() {
    this.msg = {
      "to": this.toList,
      "subject": this.subject,
      "content": this.message,
      "date": "null",
      "importance": this.importance,
    }
    if (this.toList[0] == '') {
      this.expression = 'You must write the receiver email'
      this.display = 'block'
    }
    if (this.subject == '' && this.message == '') {
      this.expression = 'You cant send empty message'
      this.display = 'block'
    }
    else {
      let x = JSON.stringify(this.msg)
      this.URL = "http://localhost:8070/Email/send"
      this.sendRequest(x)
      this.clear()
    }
  }
  draft() {
    this.msg = {
      "to": this.toList,
      "subject": this.subject,
      "content": this.message,
      "date": "null",
      "importance": this.importance
    }
    if (this.toList[0] == '' && this.subject == '' && this.message == '' && this.attachment == '') {
      this.expression = 'There is no message to save'
      this.display = 'block'
    }
    else {
      let x = JSON.stringify(this.msg)
      this.URL = "http://localhost:8070/Email/draft"
      this.sendRequest(x)
      this.clear()
    }
  }
  clear() {
    this.toList = []
    this.subject = ''
    this.message = ''
    this.attachment = ''
    this.display = 'None'
  }
  findImportant(event: any) {
    this.importance = event.target.value;
  }

  add(event:any){
     this.list.push("1");
  }
}
