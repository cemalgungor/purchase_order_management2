import {CustomerModel, ICustomer} from "./customer-model";

export interface IOrder {
  id?: number,
  orderName?: string,
  customer?:ICustomer
}
export class OrderModel implements  IOrder{
  constructor(
    public id?:number,
    public orderName?:string,
    public customer?:ICustomer
  ) {
  }


}
