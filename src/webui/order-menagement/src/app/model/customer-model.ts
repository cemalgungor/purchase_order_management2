import {orderAuthorityModel} from "./orderAuthority-model";
import {IOrder, OrderModel} from "./order-model";

export interface ICustomer{
  id?:number,
  name?:string,
  orderAuthority?:orderAuthorityModel,
  order?:IOrder[]

}
export class CustomerModel implements ICustomer{
  constructor(
    public id?:number,
    public name?:string,
    public orderAuthority?:orderAuthorityModel,
    public order?:IOrder[]
  ) {
  }
}
