package com.cemal.purchase_order_menagement.service;

import com.cemal.purchase_order_menagement.entity.Product;

import com.cemal.purchase_order_menagement.repository.IProductRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl  implements   IProductService{

    private final IProductRepo productRepo;

    public ProductServiceImpl(IProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public Product saveProduct(Product product) {

       product.setCategory(product.getCategory());
       product.setProductName(product.getProductName());
       return  productRepo.save(product);

    }

    @Override
    public Product updateProduct(Product product) {
        Product product1=productRepo.getOne(product.getId());
        product1.setProductName(product.getProductName());
        product1.setCategory(product.getCategory());
        product1.setId(product.getId());
        return productRepo.save(product1);
    }

    @Override
    public Boolean deleteProduct(Long id) {

        productRepo.deleteById(id);
        Optional<Product> getProduct= productRepo.findById(id);
        if (!getProduct.isPresent()) {
          return false;
        }
        return true;
    }
    @Cacheable(cacheNames = "products", key = "#productId")
    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }
}
