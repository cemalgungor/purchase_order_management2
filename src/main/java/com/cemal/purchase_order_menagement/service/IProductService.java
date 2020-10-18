package com.cemal.purchase_order_menagement.service;

import com.cemal.purchase_order_menagement.entity.Product;
import java.util.List;
public interface IProductService {
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    Boolean deleteProduct(Long id);
    List<Product> getAllProduct();
}
