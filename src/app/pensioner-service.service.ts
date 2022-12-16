import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login.service';
import { Observable } from 'rxjs';
import { PensionDetail } from './pension-detail';
@Injectable({
  providedIn: 'root'
})
export class PensionerServiceService {
  baseUrl="http://localhost:9084";
  constructor(private http:HttpClient,private login:LoginService) { }

  getPensionDetail(credentials:any)
  {
    return this.http.post( `${this.baseUrl}/pensionDetail`,credentials);
  }

  processPension(credentials:any)
  {
    return this.http.post(`${this.baseUrl}/processPension`,credentials,{headers:this.login.createHeader()});
  }

  CalculatePension(credentials:any)
  {
    return this.http.post(`${this.baseUrl}/pensionerInput`,credentials,{headers:this.login.createHeader()});
  }

  PensionDetail(name:any)
  {
     return this.http.get<Object>(`http://localhost:9081/pensionDetail/${name}`,{headers:this.login.createHeader()});
  }
}
