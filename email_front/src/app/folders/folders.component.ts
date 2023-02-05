import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-folders',
  templateUrl: './folders.component.html',
  styleUrls: ['./folders.component.css']
})
export class FoldersComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }
  folders: string[] = []
  answer: any;
  page: number = 1
  folderName: string = ''
  newFolder: string = ''
  oldFolder: string = ''
  deleteFolder: string = ''
  temp: string = ''
  displayAlertAdd: string = 'None'
  displayAlertRename: string = 'None'
  displayAlertdelete: string = 'None'
  displayPag: string = 'None'
  folder: any = ""
  Folder : string =""
  foldername :any
  selected: string = ''
  messages = [
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
  sendRequest(x: string, y: any,num:number) {
    let origin = "http://localhost:8060/get/expression"
    
    if(location.port == "61606") {
      origin = "http://localhost:8070/get/expression"
    }
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(origin, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          y = response;
          console.log(y)
          if(num==1)
          {
            this.messages = JSON.parse(y)
          }
          else if(num==0)
          {
            this.answer=y
          }
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
  add() {
    this.folders.push(this.folderName)
    let x = "createfile&" + this.folderName
    this.sendRequest(x,this.answer,0)
    this.displayAlertAdd = 'block'
    this.displayAlertRename = 'None'
    this.displayAlertdelete = 'None'
    this.folderName=''
  }
  rename() {
    console.log(this.oldFolder, this.newFolder)
    let x = "renamefile" + "&" + this.oldFolder + "&" + this.newFolder
    this.displayAlertRename = 'block'
    this.displayAlertAdd = 'None'
    this.displayAlertdelete = 'None'
    this.sendRequest(x, this.answer,0)
    this.oldFolder=''
    this.newFolder =''
  }
  delete() {
    let x = "deletefile" + "&" + this.deleteFolder
    this.sendRequest(x, this.answer,0)
    this.displayAlertAdd = 'None'
    this.displayAlertRename = 'None'
    this.displayAlertdelete = 'block'
    this.deleteFolder=''
  }
  showFolders() {
    let x = "showfolders"
    this.sendfile(x)
  }
  findName(event: any) {
    this.selected = event.target.value
  }
  show() {
    console.log(this.selected)
    let x = "show&" + this.selected + "&default"
    console.log(x)
    this.sendRequest(x, this.folder,1)
    
    this.displayPag = 'block'
    this.displayAlertRename = 'block'
    this.displayAlertAdd = 'None'
    this.displayAlertdelete = 'None'
  }

}
