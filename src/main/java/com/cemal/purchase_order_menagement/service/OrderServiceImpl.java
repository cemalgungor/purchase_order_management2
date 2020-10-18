package com.cemal.purchase_order_menagement.service;

import com.cemal.purchase_order_menagement.entity.*;
import com.cemal.purchase_order_menagement.repository.ICustomerRepo;
import com.cemal.purchase_order_menagement.repository.IOrderRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements  IOrderService {
    private final IOrderRepo orderRepo;
    private final CustomerServiceImpl customerService;

    private  final  ICustomerRepo customerRepo;

    public OrderServiceImpl(
                            IOrderRepo orderRepo, ICustomerRepo customerRepo,
                            CustomerServiceImpl customerService) {
        this.orderRepo = orderRepo;
        this.customerService = customerService;

        this.customerRepo=customerRepo;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        Optional<Customer> customer1=customerRepo.findById(order.getCustomer().getId());
      order.setCustomer(order.getCustomer());
      order.setOrderName(order.getOrderName());
      order.setId(order.getId());
      //order.setOrdered_product_fk(order.getOrdered_product_fk());
      if(customer1.get().getOrderAuthority().equals(OrderAuthority.HAS_NOT)){
          return null;
      }


        return orderRepo.save(order);
    }

    @Override
    public List<Order> updateOrder(Long id, Order order) {
        ArrayList<Order> order1 = new ArrayList<>();

        for (Order entity : order.getCustomer().getOrder()) {
             order1.add(entity);
        }
        List<Order> orderReturned= orderRepo.saveAll(order1);

        //order1.setCustomer_id(order.getCustomer_id());
       // order1.setOrderName(order.getOrderName());
       // Customer customer1=customerService.getOneCustomer(order.getCustomer_id().getId());
     //   if(customer1.getOrderAuthority().equals("HAS")){
           // return orderRepo.save(order1);
       // }

        return orderReturned;
    }

    @Override
    public Boolean deleteByOrderId(Long id) {

        orderRepo.deleteById(id);
        if(orderRepo.findById(id).equals(id)) {
            return false;
        }
        return true;
    }

    @Override
    public List<Order> getAllOrder() {

        return orderRepo.findAll();
    }
}
