package com.cemal.purchase_order_menagement.Dto;

import com.cemal.purchase_order_menagement.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String orderName;
    private Long customer_id;
}
