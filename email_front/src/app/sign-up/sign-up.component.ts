import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private http :HttpClient, private _router:Router) {
  
   }

  ngOnInit(): void {
  }
  answer:any
  sendRequestSignIn(x: string) {
    let origin = "http://localhost:8060/get/expression"
    
    if(location.port == "61606") {
      origin = "http://localhost:8070/get/expression"
    }
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(origin, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
           this.answer = response;
           if(this.answer == "ERROR")
              this.displaySign = "block"
            else
              this._router.navigate(['compose'])
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  sendRequestSignOut(x: string) {
    let origin = "http://localhost:8060/get/expression"
    
    if(location.port == "61606") {
      origin = "http://localhost:8070/get/expression"
    }
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post(origin, x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
           this.answer = response;
           if(this.answer=="ERROR"){
             this.displayAlert='block'
             this.expression = "This email has been used before "
             this.displayDone='None'
             this.displaySign='None'
           }
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  password: string = ""
  rePassword: string = ""
  displayAlert: string = "None"
  displayDone: string = "None"
  expression: string = ""
  data ={
    "username":"",
    "email":"",
    "password":"",
    "phonenumber":""
  }
  check : string =""
  test : string =""
  emailSignIn:string=""
  passwordSignIn:string = ""
  displaySign: string = "None"
  signIn() {
    console.log(this.emailSignIn, this.passwordSignIn)
    let signin = {
      "email": this.emailSignIn,
      "password": this.passwordSignIn
    }
    let sign = "signin&"+JSON.stringify(signin)
    console.log(sign)
    this.sendRequestSignIn(sign)
    console.log(this.answer)
  }
  signUpValidation() {
    if (this.data.username == "") {
      this.displayAlert = "block"
      this.expression = "You must enter your name"
    }
    else if (this.data.email == "") {
      this.displayAlert = "block"
      this.expression = "You must enter your email"
    }
    else if (this.password == "") {
      this.displayAlert = "block"
      this.expression = "You must enter your password"
    }
    else if (this.password != this.rePassword && this.password != "") {
      this.displayAlert = "block"
      this.expression = "The two passwords doesn't match"
    }
    else if (this.data.phonenumber == "") {
      this.displayAlert = "block"
      this.expression = "You must enter your phone"
    }
    else {
      this.data.password = this.password
      this.check = "signup&" + JSON.stringify(this.data)
      console.log(this.check) 
      this.sendRequestSignOut(this.check)
      this.clear()
      this.displayAlert = "None"
      this.displayDone = "block"
    }
  }
  none(){
     this.displayDone='None'
  }
  clear() {
    this.data.username = ""
    this.data.email = ""
    this.password = ""
    this.rePassword = ""
    this.data.phonenumber = ""
    this.data.password = ""
  }

}