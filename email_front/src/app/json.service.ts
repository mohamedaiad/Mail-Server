import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Location } from '@angular/common';
import { Router, NavigationEnd } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class JsonService {

  constructor(private http: HttpClient, private _router: Router) {

  }
  json: any;
  // location: Location;
  sendRequest(x: any) {
    if (x != '') {
      const headers = new HttpHeaders({ 'Content-Type': "application/text" })
      this.http.post("http://localhost:8080/print/calculate", x,
        { headers: headers, responseType: 'text' })
        .subscribe(response => {
          this.json = response;
        }
          , (error) => {
            console.log(error);
          });
    }
  }
  signin(email: string, passward: string) {
    console.log(email, passward)
    let signin = {
      "email": email,
      "password": passward
    }
    let sign = JSON.stringify(signin)
    console.log(sign)

    this._router.navigate(['compose'])
    // console.log(signin)
    // this.sendRequest(signin)

  }

  public data = {
    "username": "hambozoo",
    "email": "hambozzo@gmail.com",
    "password": "123456789",
    "phonenumber": "01023698547",
    "n_inbox": 1,
    "inbox": [
      {
        "to": "ham",
        "date": "1/10/2645",
        "content": "inbox"
      }
    ],

    "n_sent": 1,
    "sent": [
      {
        "to": "ham",
        "date": "1/10/2645",
        "content": "sent"
      }
    ],
    "n_draft": 16,
    "draft": [
      {
        "id": "1",
        "to": "michael",
        "date": "1/10/2645",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "2",
        "to": "ahmed",
        "date": "1/10/2695",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "3",
        "to": "hambo",
        "date": "1/10/2545",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "4",
        "to": "hamboz",
        "date": "1/10/2605",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "5",
        "to": "ham",
        "date": "1/10/2645",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "6",
        "to": "hamb",
        "date": "1/10/2695",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "7",
        "to": "hambo",
        "date": "1/10/2545",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "8",
        "to": "hamboz",
        "date": "1/10/2605",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "9",
        "to": "ham",
        "date": "1/10/2645",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "10",
        "to": "hamb",
        "date": "1/10/2695",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "11",
        "to": "hambo",
        "date": "1/10/2545",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "12",
        "to": "hamboz",
        "date": "1/10/2605",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "13",
        "to": "ham",
        "date": "1/10/2645",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "14",
        "to": "hamb",
        "date": "1/10/2695",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "15",
        "to": "hambo",
        "date": "1/10/2545",
        "content": "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat molestiae recusandae nam vero aliquam totam maiores illo harum eum dolores hic officia reiciendis, animi et quibusdam non? Odio maiores numquam totam expedita sequi voluptatum qui temporibus. Doloribus, laboriosam quidem. Quisquam, necessitatibus. Quas voluptates explicabo suscipit harum modi nihil ut numquam quis eos? Asperiores quas laudantium accusantium vel inventore nostrum cum dolor molestias error numquam. Quas libero exercitationem eos sed nam nemo doloremque excepturi non modi impedit architecto voluptatem molestiae, vel deleniti sapiente repudiandae facilis error. Aut ipsum impedit error odio dolores consequatur totam ut ullam maxime tenetur? Dolorum, unde ullam."
      },
      {
        "id": "16",
        "to": "hamboz",
        "date": "1/10/2605",
        "content": "Lorem ipsum  totam ut ullam maxime tenetur? Dolorum, unde ullam."
      }
    ],
  }
}
