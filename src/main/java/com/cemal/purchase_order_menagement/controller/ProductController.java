package com.cemal.purchase_order_menagement.controller;

import com.cemal.purchase_order_menagement.entity.Customer;
import com.cemal.purchase_order_menagement.entity.Order;
import com.cemal.purchase_order_menagement.entity.Product;
import com.cemal.purchase_order_menagement.service.IProductService;
import com.cemal.purchase_order_menagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@Api(value = ApiPaths.ProductCtrl.CTRL, description = "Order APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Get All Product", response = Order.class ,responseContainer = "List")
    @GetMapping()
    public ResponseEntity<List<Product>> getAllTasks() {
        List<Product> product = productService.getAllProduct();
        return ResponseEntity.ok(product);
    }
    @ApiOperation(value = "Add new Product", response = Order.class)
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addCustomer(@Validated @RequestBody Product product) {
        Product product1 = productService.saveProduct(product);
        return ResponseEntity.ok(product1);
    }
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>  deleteProduct(@PathVariable(value = "id", required = true) Long id) {

        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    @ApiOperation(value = "update old Product", response = Order.class)
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@Validated   @RequestBody Product product)
            {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

}
