package com.cemal.purchase_order_menagement.repository;

import com.cemal.purchase_order_menagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrderRepo extends JpaRepository<Order,Long> {

}
