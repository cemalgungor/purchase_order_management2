import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable, of  } from 'rxjs';
import {catchError, map} from "rxjs/operators";
import {ICustomer} from "../model/customer-model";
import {IOrder} from "../model/order-model";


@Injectable({ providedIn: 'root' })
export class CustomerService {
  constructor(protected http: HttpClient) {}
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getAllCustomer(): Observable<ICustomer[]> {
    return this.http.get('http://localhost:8080/api/customer' ).pipe(
      map((res) => {
        console.log(res);
        return <ICustomer[]> res
      })
    )
  }
  addCustomer(customer:ICustomer): Observable<any> {
    const body = {
      id:customer.id,
      name:customer.name,
      orderAuthority:customer.orderAuthority
    }
    return this.http.post( 'http://localhost:8080/api/customer/addCustomer',  body , this.httpOptions);
  }

  updateCustomer(customer:ICustomer): Observable<any> {
    const body = {
      id:customer.id,
      name:customer.name,
      orderAuthority:customer.orderAuthority

    }
    return this.http.put( 'http://localhost:8080/api/customer/update',  body , this.httpOptions);
  }
  deleteProduct(id: number): Observable<any> {

    return this.http.delete( 'http://localhost:8080/api/customer/delete/'+id,  this.httpOptions);
  }

  private formatError(error: any) {
    return of("http://localhost:8000/api" + error.error);
  }
  addOrderWithCustomer(id,selectedOrder): Observable<ICustomer> {
    var newArray = selectedOrder.map(function(item) {
        return {

          'orderName': item
        }
      }
    );
    console.log(newArray);

    const body = {
      id: id,
      order:
        newArray


      };
    console.log(body);
    return this.http.post( 'http://localhost:8080/api/customer/addCustomerWithOrder',  body , this.httpOptions);
  }
  updateOrderWithCustomer(id,updatedOrder): Observable<ICustomer> {
    var newArray = updatedOrder.map(function(item) {
        return {
           'id':id,
          'orderName': item
        }
      }
    );
    console.log(newArray);

    const body = {
      id: id,
      order:
      newArray


    };
    console.log(body);
    return this.http.post( 'http://localhost:8080/api/customer/updateCustomerWithOrder',  body , this.httpOptions);
  }


}
