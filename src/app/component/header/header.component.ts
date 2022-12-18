import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  signout(){
    sessionStorage.clear()
    this.router.navigate(['/']);
  }
  asset(){
    this.router.navigate(['sellAsset']);
  }
  dashboard(){
    this.router.navigate(['dashboard']);
  }
}
