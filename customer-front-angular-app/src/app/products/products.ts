import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class Products implements OnInit{
  products!: any;
  constructor(private http: HttpClient) {
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8098/products").subscribe({
      next: data =>{
        this.products = data;
        console.log(this.products)
      },
      error: err => {
        console.log("Error in fetching products ", err)
      }
    })
  }

}
