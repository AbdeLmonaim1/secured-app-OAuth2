import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Customers } from './customers/customers';
import { Products } from './products/products';


import {HttpClientModule} from '@angular/common/http';



@NgModule({
  declarations: [
    App,
    Customers,
    Products
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,

  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),

  ],
  bootstrap: [App]
})
export class AppModule { }
