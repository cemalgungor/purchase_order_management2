package com.cemal.purchase_order_menagement.service;

import com.cemal.purchase_order_menagement.Dto.OrderDto;
import com.cemal.purchase_order_menagement.entity.Order;

import java.util.List;

public interface IOrderService {
    Order saveOrder(Order order);
    List<Order> updateOrder(Long customer_id,Order order);
    Boolean deleteByOrderId(Long id);
    List<Order> getAllOrder();
}
