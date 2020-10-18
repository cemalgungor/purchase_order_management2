package com.cemal.purchase_order_menagement.controller;



import com.cemal.purchase_order_menagement.Dto.OrderDto;
import com.cemal.purchase_order_menagement.entity.Customer;
import com.cemal.purchase_order_menagement.entity.Order;
import com.cemal.purchase_order_menagement.service.IOrderService;
import com.cemal.purchase_order_menagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@Api(value = ApiPaths.OrderCtrl.CTRL, description = "Order APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }
    @ApiOperation(value = "Get All Order", response = Order.class ,responseContainer = "List")
    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> order = orderService.getAllOrder();
        return ResponseEntity.ok(order);
    }
    @ApiOperation(value = "Add new Order", response = Order.class)
    @PostMapping("/addOrder")
    public  ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order order1=orderService.saveOrder(order);
             return ResponseEntity.ok(order1);
    }
    @ApiOperation(value = "update old Order", response = Order.class ,responseContainer = "List")
    @PutMapping("/updateOrder/{id}")
    public  ResponseEntity<List<Order>> updateOrder(@PathVariable(value = "id", required = true) Long id,@RequestBody Order order){
       List<Order> order1=orderService.updateOrder(id,order);
        return ResponseEntity.ok(order1);
    }
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(orderService.deleteByOrderId(id));
    }
}
