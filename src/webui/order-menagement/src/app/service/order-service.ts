import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable, of  } from 'rxjs';

import {catchError, map} from "rxjs/operators";
import {IOrder} from "../model/order-model";
@Injectable({ providedIn: 'root' })
export class OrderService {
  constructor(protected http: HttpClient) {}
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getAllOrder(): Observable<IOrder[]> {
    return this.http.get('http://localhost:8080/api/order' ).pipe(
      map((res) => {
        console.log(res);
        return <IOrder[]> res
      })
    )
  }
  addOrder(order:IOrder): Observable<any> {
    const body = {
      orderName:order.orderName,
    customer_id:{
        id:order.customer.id
    }
    }


    return this.http.post( 'http://localhost:8080/api/order/addOrder',  body , this.httpOptions);
  }
  deleteOrder(id: number): Observable<any> {

    return this.http.delete( 'http://localhost:8080/api/order/delete/'+id,  this.httpOptions);
  }

  private formatError(error: any) {
    return of("http://localhost:8000/api" + error.error);
  }
}
