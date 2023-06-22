import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login-component/login.component';
import { PharmacistLoginComponent } from './pharmacist-login/pharmacist-login.component';
import { AdminDoctorLoginComponent } from './admin-doctor-login/admin-doctor-login.component';

@NgModule({
  declarations: [LoginComponent, PharmacistLoginComponent, AdminDoctorLoginComponent],
  imports: [CommonModule],
})
export class AuthenticationModule {}
