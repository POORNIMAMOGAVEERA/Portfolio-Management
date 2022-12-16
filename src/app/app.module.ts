import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { PensionerListComponent } from './pensioner-list/pensioner-list.component';
import { PensionDisburseComponent } from './pension-disburse/pension-disburse.component';
import { PensionerInputComponent } from './pensioner-input/pensioner-input.component';
import { NavbarComponent } from './navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon'
import { MatTabsModule } from '@angular/material/tabs';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDialogModule } from '@angular/material/dialog';
const appRoutes:Routes=[
{path:'home',component:HomeComponent},
{
  path:'login',component:LoginComponent,
},
{
  path:'pensioner-input',component:PensionerInputComponent
},
{
  path:'pensioner-list',component:PensionerListComponent
},
{
  path:'pension-disburse',component:PensionDisburseComponent
},
{
  path:'profile',component:ProfileComponent
}]
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    PensionerListComponent,
    PensionDisburseComponent,
    PensionerInputComponent,
    NavbarComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatTableModule,
    HttpClientModule,
    MatIconModule,
    MatTabsModule,
    MatCardModule,
    MatPaginatorModule,
    MatDialogModule,
    RouterModule.forRoot(appRoutes) 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
