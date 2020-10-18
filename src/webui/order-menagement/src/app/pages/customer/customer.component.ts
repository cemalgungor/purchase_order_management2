import {Component, OnInit, TemplateRef} from '@angular/core';
import {ICustomer} from "../../model/customer-model";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {ProductService} from "../../service/product-service";
import {CustomerService} from "../../service/customer-service";
import {IProduct} from "../../model/product-model";
import {IOrder} from "../../model/order-model";

import {OrderService} from "../../service/order-service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {
  customer?:ICustomer[];
  modalRef: BsModalRef;
  authValue?:number;
  customerId: any;
  selectedOrder?:IOrder[];
  updatedOrder?:IOrder[];
  product?: IProduct[];
  profileForm = new FormGroup({
    customerName: new FormControl('')

  });
  updateCustomerForm = new FormGroup({
    customerName: new FormControl('')

  });


  constructor(  private modalService: BsModalService,
                private  productService:ProductService,
                protected customerService: CustomerService,
                private  orderService: OrderService
                ) { }

  ngOnInit(): void {
    this.customerService.getAllCustomer().subscribe((customer: ICustomer[]) => this.customer = customer);
    this.productService.getAllProduct().subscribe((product: IProduct[]) => this.product = product);
    this.selectedOrder=[];
  }
  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
  deleteCustomer(id:number) {
    return this.customerService.deleteProduct(id).subscribe( (data)=>{
        const deletedCustomer = this.customer.find(x => x.id === id);
        console.log(deletedCustomer);
        this.customer.splice(this.customer.indexOf(deletedCustomer), 1);

      }
    );
  }
  deleteOrder(id:number) {
    return this.orderService.deleteOrder(id).subscribe(
    );
  }
  selectedValue(name: any) {
    console.log(name);
    if(name=="HAS") {
      this.authValue = 0;
      console.log(this.authValue);
    }
    else{
    this.authValue = 1;
    console.log(this.authValue);
    }

  }

  addCustomer() {
    const body = {
      name: this.profileForm.value['customerName'],
      orderAuthority: this.authValue
    }
    return this.customerService.addCustomer(body).subscribe(

    );
  }
  updateCustomer(id:number) {
    const body = {
       id:id,
      name: this.updateCustomerForm.value['customerName'],
      orderAuthority: this.authValue
    }
    return this.customerService.updateCustomer(body).subscribe(

    );
  }
  addOrder() {


     console.log(this.selectedOrder);
    return this.customerService.addOrderWithCustomer(this.customerId,this.selectedOrder).subscribe(

    );
  }

  updateOrder() {

    console.log(this.selectedOrder);
    return this.customerService.addOrderWithCustomer(this.customerId,this.selectedOrder).subscribe(

    );
  }
  selectedId(id: any) {
    this.customerId = id;
    console.log(id);
  }
  selectedOrders(name: any) {

    this.selectedOrder.push(name);

  }
  selectedOrdersForUpdate(id:number,name: any) {

    this.updatedOrder.push(name);

  }


}
