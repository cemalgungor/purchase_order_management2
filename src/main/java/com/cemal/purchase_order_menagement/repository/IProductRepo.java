package com.cemal.purchase_order_menagement.repository;

import com.cemal.purchase_order_menagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
}
