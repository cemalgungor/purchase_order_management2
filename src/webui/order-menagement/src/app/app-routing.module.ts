import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OrderComponent} from "./pages/order/order.component";
import {ProductComponent} from "./pages/product/product.component";
import {CustomerComponent} from "./pages/customer/customer.component";


const routes: Routes = [
  {path: '', component: OrderComponent},
  {path: 'product', component: ProductComponent},
  {path: 'customer', component: CustomerComponent},


];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
