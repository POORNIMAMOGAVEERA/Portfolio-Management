import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PensionDetail } from '../pension-detail';
import { PensionerServiceService } from '../pensioner-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  pension={
    aadhaarNumber:"",
    name:"",
    dateOfBirth:"",
    panNumber:"",
    salary:null,
    allowance:null,
    pensionType:"",
  };
  Name:any;
  constructor(private router:Router,private service:PensionerServiceService ) { }

  ngOnInit(): void {
  this.Name=sessionStorage.getItem('userName');
  this.service.PensionDetail(this.Name).subscribe((data:any)=>{
    this.pension=data;
    console.log(this.pension);
  })
  }
  logout(){
    sessionStorage.clear();
    this.router.navigate(['login']);
  }
}
