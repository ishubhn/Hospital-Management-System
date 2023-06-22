import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationModule } from './authentication/authentication.module';
import { AdminModule } from './admin/admin.module';
import { UserModule } from './user/user.module';
import { PharmacyModule } from './pharmacy/pharmacy.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthenticationModule,
    AdminModule,
    UserModule,
    PharmacyModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
