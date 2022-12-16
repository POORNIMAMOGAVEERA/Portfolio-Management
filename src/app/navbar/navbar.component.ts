import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public loggedIn=false;

  userName:any;

  constructor(private loginService:LoginService) { }

  ngOnInit(): void {

    this.loggedIn=this.loginService.isLoggedIn();
    this.userName=this.loginService.loginUserName();

  }

  logoutUser()
  {
    this.loginService.logout();
    location.reload();
  }

}