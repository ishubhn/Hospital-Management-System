import { CommonModule } from "@angular/common";
import { AdminDoctorLoginComponent } from "./admin-doctor-login/admin-doctor-login.component";
import { LoginComponent } from "./login/login.component";
import { PharmacistLoginComponent } from "./pharmacist-login/pharmacist-login.component";
import { NgModule } from "@angular/core";

@NgModule({
  declarations: [
    LoginComponent,
    AdminDoctorLoginComponent,
    PharmacistLoginComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    LoginComponent,
    AdminDoctorLoginComponent,
    PharmacistLoginComponent
  ]
})
export class AuthenticationModule { }