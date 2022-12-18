import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userName:string=''
  password:string=''
  
  constructor(private authService:AuthServiceService,private route:Router) { }

  ngOnInit(): void {
    sessionStorage.clear()
  }
   login(){
      this.authService.Login(this.userName,this.password).subscribe(Data=>{this.handleResponce(Data)},
      error=>alert("Check the Login Credential"))
   }
   handleResponce(responce:any){
    sessionStorage.setItem('userId',responce.userid)
    console.log(JSON.stringify(responce.userid))
    var userId=JSON.stringify(responce.userid)
    localStorage.setItem('userid',userId )
    let tokenStr= 'Bearer '+responce.authToken 
    sessionStorage.setItem('token', tokenStr)
    var value="true"
    sessionStorage.setItem('isLoggedIn',value )
    this.route.navigate(['/dashboard'])
  }
}
