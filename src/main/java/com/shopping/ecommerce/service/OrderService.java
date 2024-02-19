package com.shopping.ecommerce.service;


import com.shopping.ecommerce.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService 
{

	public OrderDetail createOrder(OrderDetail order, int cid, int pid);
	public List<OrderDetail> showOrdersList(int id);

}
