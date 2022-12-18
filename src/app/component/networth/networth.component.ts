import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/auth-service.service';
import { Mutual } from 'src/app/models/mutual';
import { Portfolio } from 'src/app/models/portfolio';
import { Stock } from 'src/app/models/stock';
import { MatTableDataSource } from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon'
import { FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-networth',
  templateUrl: './networth.component.html',
  styleUrls: ['./networth.component.css']
})
export class NetworthComponent implements OnInit {
  id: number | any;
  stock:Stock[]| any;
  mutual:Mutual[]|any;
  worth:DoubleRange | any;
  show1: boolean = true;
  displayedColumns1: string[] = ['Stockname', 'Price'];
  displayedColumns2: string[] = ['Fund', 'Price'];
  displayedColumns3: string[] = ['Stockname', 'Count'];
  displayedColumns4: string[] = ['Fund', 'Count'];
  dataSource4: any;
  dataSource3: any;
  constructor(private router:Router,private auth:AuthServiceService) { }

  ngOnInit(): void {
    
    this.auth.getAllStocks().subscribe(data=>{
      this.stock=data;
      console.log(this.stock);
    },(error: any)=>alert("Something Went Wrong"));
    this.auth.getAllMutual().subscribe(data=>{
        this.mutual=data;
        console.log(this.mutual);
    });
    this.id = Number(localStorage.getItem('userid'));
    this.auth.networth(this.id).subscribe(data=>{
    this.worth=data;
    console.log(this.worth);
   });
   this.auth.getUserById(this.id).subscribe(data=>{
    this.dataSource3 = new MatTableDataSource<Stock>(
      data.stock
    );
    this.dataSource4 = new MatTableDataSource<Mutual>(
      data.mutual     
    );
  });

}
  
   
}

