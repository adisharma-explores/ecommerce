package com.shopping.ecommerce.controller;

import com.shopping.ecommerce.model.OrderDetail;
import com.shopping.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController 
{
	@Autowired
	OrderService orderService;
	@PostMapping("/createOrder/{custid}/{prodid}")
	public OrderDetail createOrder(@PathVariable int custid, @PathVariable int prodid , @RequestBody OrderDetail orderJson)
	{
		return orderService.createOrder(orderJson, custid, prodid);
		}
	@GetMapping("/Order/{id}")
	public List<OrderDetail> showOrder(@PathVariable int id){
		
		return orderService.showOrdersList(id);
		
	}
	
}
