import {Component, OnInit, TemplateRef} from '@angular/core';
import {OrderService} from "../../service/order-service";
import {IOrder} from "../../model/order-model";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {ProductService} from "../../service/product-service";
import {IProduct} from "../../model/product-model";
import {FormControl, FormGroup} from "@angular/forms";
import {CustomerService} from "../../service/customer-service";
import {ICustomer} from "../../model/customer-model";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  order?: IOrder[];
  customer?:ICustomer[]
  customerId: any;
  selectedOrder?:IOrder[];
  name: any;
  product?: IProduct[];
  modalRef: BsModalRef;
  profileForm = new FormGroup({
    orderName: new FormControl('')

  });

  constructor(protected orderService: OrderService,
              private modalService: BsModalService,
              protected productService: ProductService,
              protected customerService: CustomerService
  ) {
  }
  deleteOrder(id:number) {
    return this.orderService.deleteOrder(id).subscribe( (data)=>{
        const deletedTask = this.order.find(x => x.id === id);
        console.log(deletedTask);
        this.order.splice(this.order.indexOf(deletedTask), 1);

      }
    );
  }
  ngOnInit(): void {
    this.orderService.getAllOrder().subscribe((order: IOrder[]) => this.order = order);
    this.productService.getAllProduct().subscribe((product: IProduct[]) => this.product = product);
    this.customerService.getAllCustomer().subscribe((customer: ICustomer[]) => this.customer = customer);

  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }


  addOrder() {
    const body = {
      customer_id: this.customerId,
       orderName: this.name
    }
    return this.orderService.addOrder(body).subscribe(

    );
  }



  selectedId(id: any) {
    this.customerId = id;
    console.log(id);
  }
  selectedValue(name: any) {
    console.log(name);
    this.name = name;

  }
  selectedOrders(name: any) {
    console.log(name);
    this.selectedOrder = name;

  }


}
