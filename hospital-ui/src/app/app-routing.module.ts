import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { AdminDoctorLoginComponent } from './authentication/admin-doctor-login/admin-doctor-login.component';
import { PharmacistLoginComponent } from './authentication/pharmacist-login/pharmacist-login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'hospital/login', component: AdminDoctorLoginComponent },
  { path: 'pharmacy/login', component: PharmacistLoginComponent },
  { path: '**', redirectTo: 'login' }
  // Add other routes for different modules and components
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
