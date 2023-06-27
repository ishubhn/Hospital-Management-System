import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AdminDoctorLoginComponent } from "./admin-doctor-login/admin-doctor-login.component";
import { LoginComponent } from "./login/login.component";
import { PharmacistLoginComponent } from "./pharmacist-login/pharmacist-login.component";
import { AuthenticationRoutingModule } from "./authentication-routing.module";
import { SharedModule } from "../shared/shared.module";
import { FormsModule } from "@angular/forms";

@NgModule({
  declarations: [
    LoginComponent,
    AdminDoctorLoginComponent,
    PharmacistLoginComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserAnimationsModule,
    AuthenticationRoutingModule,
    SharedModule
  ],
  exports: [
    LoginComponent,
    AdminDoctorLoginComponent,
    PharmacistLoginComponent
  ]
})
export class AuthenticationModule { }