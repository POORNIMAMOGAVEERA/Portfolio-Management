import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import Swal from 'sweetalert2';
import { LoginService } from '../login.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentials={
    userName:'',
    password:''
  }

  constructor(private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {
  }
  OnSubmit()
  {
    if(this.credentials.userName!='' && this.credentials.password!='')
    {
      sessionStorage.setItem('userName',this.credentials.userName);
      console.log("login details submitted");

      this.loginService.doLogin(this.credentials).subscribe(
        (response:any)=>{ 
          console.log(typeof(response.token))
          var tokenStr=response.token
          console.log(tokenStr) 
          var Token= 'Bearer '+tokenStr
          console.log(Token)
          sessionStorage.setItem('token', Token)
          
          this.router.navigate(['/pensioner-list'])
        },
        (error:any)=>{
          //Swal.fire('Invalid!','Wrong username or password','error');     
        } 
      )

    }else{
      console.log("empty fields");
    }
  }
}
