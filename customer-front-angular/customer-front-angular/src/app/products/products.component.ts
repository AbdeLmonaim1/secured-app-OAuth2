import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css',


})
export class ProductsComponent implements OnInit{
  products: any;
  constructor(private http: HttpClient) {
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8098/products")
      .subscribe({
        next: data => {
        this.products=data;
        console.log(this.products)
        },
        error: err => {
        console.log(err);
        }
      })
  }

}
