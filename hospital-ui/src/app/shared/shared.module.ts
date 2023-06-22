import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FootbarComponent } from './footbar/footbar.component';
import { NavbarComponent } from './navbar/navbar.component';



@NgModule({
  declarations: [
    FootbarComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
