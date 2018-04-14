import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app.routing';
import { AuthenticationModule } from './authentication/authentication.module';
import { CoreModule } from './core/core.module';
import { NavigationModule } from './navigation/navigation.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    AppRoutingModule,
    AuthenticationModule,
    BrowserModule,
    CoreModule,
    NavigationModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
