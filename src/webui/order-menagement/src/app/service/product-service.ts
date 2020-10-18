import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable, of  } from 'rxjs';

import {catchError, map} from "rxjs/operators";
import {IProduct} from "../model/product-model";
import {IOrder} from "../model/order-model";

@Injectable({ providedIn: 'root' })
export class ProductService {
  constructor(protected http: HttpClient) {}
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getAllProduct(): Observable<IProduct[]> {
    return this.http.get('http://localhost:8080/api/product' ).pipe(
      map((res) => {
        console.log(res);
        return <IProduct[]> res
      })
    )
  }
  addProduct(product:IProduct): Observable<any> {
    const body = {
      productName:product.productName,
       category:product.category
      }
    return this.http.post( 'http://localhost:8080/api/product/addProduct',  body , this.httpOptions);
    }
  updateProduct(product:IProduct): Observable<any> {
    const body = {
      productName:product.productName,
      category:product.category,
      id:product.id
    }
    return this.http.put( 'http://localhost:8080/api/product/update',  body , this.httpOptions);
  }
  deleteProduct(id: number): Observable<any> {

    return this.http.delete( 'http://localhost:8080/api/product/delete/'+id,  this.httpOptions);
  }

  private formatError(error: any) {
    return of("http://localhost:8000/api" + error.error);
  }
}
