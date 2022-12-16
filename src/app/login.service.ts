import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  url="http://localhost:9084"

  constructor(private http:HttpClient) { }

  doLogin(credentials:any)
  {
    return this.http.post(`${this.url}/token`,credentials);
  }

  getPensionerList()
  {
    return this.http.get<[]>(`${this.url}/details`);
  }

  loginUser(token:any,credentials:any)
  {
    localStorage.setItem('token',token);
    localStorage.setItem('uname',credentials.userName);
    return true;
  }

  loginUserName()
  {
    return localStorage.getItem('uname');
  }

  isLoggedIn()
  {
      let token=localStorage.getItem('token');
      if(token==undefined||token==''||token==null)
      {
        return false;
      }else{

        return true;
      }
  }
  logout()
  {
    localStorage.removeItem('token');
    return true;
  }

  getToken()
  {
    return localStorage.getItem('token');
  }
  createHeader(){
    let token= sessionStorage.getItem('token')
    if(token!==null){
      var header=new HttpHeaders({Authorization:token})
      return header
    }
    return
  }
}
