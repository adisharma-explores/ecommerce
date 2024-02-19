package com.shopping.ecommerce.service;


import com.shopping.ecommerce.model.Customer;
import com.shopping.ecommerce.model.OrderDetail;
import com.shopping.ecommerce.model.Product;
import com.shopping.ecommerce.repository.CustomerRepository;
import com.shopping.ecommerce.repository.OrderRepository;
import com.shopping.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderRepository orderRepository;

	@Override
	public OrderDetail createOrder(OrderDetail order, int cid, int pid) {
		Customer customer=customerRepository.findById(cid).orElse(null);
		order.setCustomer(customer);
		Product product= productRepository.findById(pid).orElse(null);
		order.setProduct(product);
		return orderRepository.save(order);
	}
	public List<OrderDetail> showOrdersList(int id){
		Customer customer=customerRepository.findById(id).orElse(null);
		return orderRepository.getOrdList(customer);
	}

	
}
