import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import User from "../models/User";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private path = "/v1/user";

  constructor(private http: HttpClient) {
  }

  getUserListTest(data?: any): Observable<any> {
    return  this.http.post(`${environment.api}${this.path}/getUserListTest`, data);
  }

  login(user: User): Observable<any> {
    return this.http.post(`${environment.api}/v1/auth/login`, user);
  }

}
