package com.cemal.purchase_order_menagement.controller;


import com.cemal.purchase_order_menagement.entity.Customer;
import com.cemal.purchase_order_menagement.service.ICustomerService;
import com.cemal.purchase_order_menagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@Api(value = ApiPaths.CustomerCtrl.CTRL, description = "Customer APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {
    private final ICustomerService customerService ;

    public CustomerController(ICustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping()
    @ApiOperation(value = "Get All Customer", response = Customer.class ,responseContainer = "List")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> customer = customerService.getAllCustomer();
        return ResponseEntity.ok(customer);
    }
    @ApiOperation(value = "Get one Customer with given id", response = Customer.class)
    @GetMapping("/oneCustomer/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable(value = "id", required = true) Long id) {
        Customer customer = customerService.getOneCustomer(id);
        return ResponseEntity.ok(customer);
    }
    @ApiOperation(value = "Add new Customer", response = Customer.class)
    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@Validated @RequestBody Customer customer) {
        Customer customer1 = customerService.saveCustomer(customer);
        return ResponseEntity.ok(customer1);
    }

    @ApiOperation(value = "Add new orders for spesific customer", response = Customer.class)
    @PostMapping("/addCustomerWithOrder")
    public ResponseEntity<Customer> addCustomerWithOrder(@Validated @RequestBody Customer customer) {
        Customer customer1 = customerService.saveCustomerWithOrder(customer);
        return ResponseEntity.ok(customer1);
    }
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>  deleteCustomer(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
    @ApiOperation(value = "update customer", response = Customer.class)
    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@Validated   @RequestBody Customer customer){
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }
    @ApiOperation(value = "update old orders for spesific customer", response = Customer.class)
    @PutMapping("/updateCustomerWithOrder")
    public ResponseEntity<Customer> updateCustomerWithOrder(@Validated   @RequestBody Customer customer){

        return ResponseEntity.ok(customerService.updateCustomerWithOrder(customer));
    }
}
