package com.cemal.purchase_order_menagement.service;

import com.cemal.purchase_order_menagement.entity.*;
import com.cemal.purchase_order_menagement.exception.NotFoundAuthorityException;
import com.cemal.purchase_order_menagement.repository.ICustomerRepo;
import com.cemal.purchase_order_menagement.repository.IOrderRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements  ICustomerService {
    private final ICustomerRepo customerRepo;
    private final IOrderRepo orderRepo;

    public CustomerServiceImpl(ICustomerRepo customerRepo,
                               IOrderRepo orderRepo
    ) {
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        customer.setName(customer.getName());
        customer.setOrderAuthority(customer.getOrderAuthority());
        customer.setId(customer.getId());
        return customerRepo.save(customer);
    }

    @Override
    public Customer saveCustomerWithOrder(Customer customer) {
        Customer customer1=customerRepo.getOne(customer.getId());
        if(customer1.getOrderAuthority().equals(OrderAuthority.HAS_NOT)){
         throw new NotFoundAuthorityException("Has not authority with id " + customer.getId());
        }
        ArrayList<Order> order1 = new ArrayList<>();
      for (Order entity : customer.getOrder()) {
          order1.add(entity);
      }
      orderRepo.saveAll(order1);
        return null;
    }
    @Override
    public Customer updateCustomerWithOrder(Customer customer) {
        ArrayList<Order> order1 = new ArrayList<>();
        for (Order entity : customer.getOrder()) {
            order1.add(entity);
        }
        orderRepo.saveAll(order1);

        return  null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customer1=customerRepo.getOne(customer.getId());

        customer1.setName(customer.getName());
        customer1.setId(customer.getId());
        customer1.setOrderAuthority(customer.getOrderAuthority());
        return customerRepo.save(customer1);
    }



    @Override
    public Boolean deleteCustomer(Long id) {

        customerRepo.deleteById(id);
        Optional<Customer> getCustomer= customerRepo.findById(id);
        return true;
    }

    @Override
    public Customer getOneCustomer(Long id) {
        Customer customerxx=customerRepo.getOne(id);
        customerxx.toString();
       return customerxx;
    }

    @Override
    public List<Customer> getAllCustomer() {

        return customerRepo.findAll();
    }
}
