import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { PharmacistLoginComponent } from './pharmacist-login/pharmacist-login.component';
import { AdminDoctorLoginComponent } from './admin-doctor-login/admin-doctor-login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'pharmacy/login', component: PharmacistLoginComponent },
  { path: 'hospital/login', component: AdminDoctorLoginComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthenticationRoutingModule {}
