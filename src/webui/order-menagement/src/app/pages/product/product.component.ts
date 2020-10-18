import {Component, OnInit, TemplateRef} from '@angular/core';
import {IProduct} from "../../model/product-model";
import {OrderService} from "../../service/order-service";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {ProductService} from "../../service/product-service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  product?: IProduct[];
  modalRef: BsModalRef;
  profileForm = new FormGroup({
    productName: new FormControl(''),
    category: new FormControl('')

  });
  constructor(
              private modalService: BsModalService,
              protected productService: ProductService
  ) {
  }
  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit(): void {
    this.productService.getAllProduct().subscribe((product: IProduct[]) => this.product = product);
  }

  addProduct() {
    const body = {
      productName: this.profileForm.value['productName'],
      category: this.profileForm.value['category']
    }
    return this.productService.addProduct(body).subscribe(

    );
  }
  updateProduct(id:number) {
    const body = {

      productName: this.profileForm.value['productName'],
      category: this.profileForm.value['category'],
      id:id
    }
    return this.productService.updateProduct(body).subscribe(

    );
  }
  deleteProduct(id:number) {
    return this.productService.deleteProduct(id).subscribe( (data)=>{
        const deletedProduct = this.product.find(x => x.id === id);
        console.log(deletedProduct);
        this.product.splice(this.product.indexOf(deletedProduct), 1);

      }
    );
  }

}
