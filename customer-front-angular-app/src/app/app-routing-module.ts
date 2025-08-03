import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Customers} from './customers/customers';
import {Products} from './products/products';

const routes: Routes = [
  {path: 'customers', component: Customers},
  {path: 'products', component: Products},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
