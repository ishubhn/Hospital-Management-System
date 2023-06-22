import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 

  // *exports: our module is providing these to other modules when they get imported
  // *bootstrap: Defines the root-component of the Application. Only use this in the AppModule.
  // *declarations: define all the components, directives and pipes, 
  //    that are declared and used inside this module if not it will throw compile time error
  // *imports: import as many sub-modules as you like
  // *providers: how dependencies are created and made available for dependency injection
  //    Any sub-components or modules can then get the same instance of that @Injectable via dependency injection
}
