import { Component, OnInit } from '@angular/core';
import { PensionDetail } from '../pension-detail';
import { PensionerServiceService } from '../pensioner-service.service';
import { MatTableDataSource } from '@angular/material/table';
@Component({
  selector: 'app-pensioner-input',
  templateUrl: './pensioner-input.component.html',
  styleUrls: ['./pensioner-input.component.css']
})
export class PensionerInputComponent implements OnInit {

  displayedColumns1: string[] = ['name', 'DOB','panNumber','pensionType','pensionAmount'];
  displayedColumns2: string[] = ['Fund', 'Price'];
  credentials={
    name:"",
    dateOfBirth:"",
    panNumber:"",
    aadhaarNumber:"",
    pensionType:""
  }
  result:any;

  pension:PensionDetail[]=[];


  constructor(private pensionService:PensionerServiceService) { }

  ngOnInit(): void {
  }

  onSubmit()
  {
  

    this.pensionService.CalculatePension(this.credentials).subscribe(
      (pensiondetail:any)=>
      {
        this.pension.push(pensiondetail);
      },
      (error: any)=>
      {
        console.log(this.credentials);
        console.log(error);
        
      }
    );
      
  }

}
