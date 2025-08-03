import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Customers} from './customers/customers';
import {Products} from './products/products';
import {AuthGuard} from './guards/auth-guard';

const routes: Routes = [
  {path: 'customers', component: Customers},
  {path: 'products', component: Products, canActivate: [AuthGuard], data: {roles: ['ADMIN']}},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
