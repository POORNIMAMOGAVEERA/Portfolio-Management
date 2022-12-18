import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AssetSaleResponse } from './models/asset';
import { Mutual } from './models/mutual';
import { Portfolio } from './models/portfolio';
import { Stock } from './models/stock';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private http:HttpClient,private route:Router) { }
 private baseURL="http://localhost:7500";
 
  Login(name:String,pass:String){
     return this.http.post(`${this.baseURL}/calculate/login`,{userName:name,password:pass})
  }

   networth(id:number):Observable<any>{
    return this.http.get<DoubleRange>(`${this.baseURL}/calculate/calculateNetworth/${id}`,{headers:this.createHeader()});
   }

  sellAsset(detail:Portfolio):Observable<Object>{
    return this.http.post<AssetSaleResponse>(`${this.baseURL}/calculate/sellAssets`,detail,{headers:this.createHeader()});
  }

  getUserById(id:number):Observable<any>{
    return this.http.get<Portfolio>(`${this.baseURL}/calculate/portfolioById/${id}`,{headers:this.createHeader()});
  }

  getAllStocks():Observable<Stock[]>{
    return this.http.get<Stock[]>(`${this.baseURL}/stock/all`,{headers:this.createHeader()});
  }
  getAllMutual():Observable<Mutual[]>{
    return this.http.get<Mutual[]>(`${this.baseURL}/mutualfund/all`,{headers:this.createHeader()});
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
 