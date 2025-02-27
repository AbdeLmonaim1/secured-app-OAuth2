import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {ProductsComponent} from './products/products.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, ProductsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'customer-front-angular';
}
