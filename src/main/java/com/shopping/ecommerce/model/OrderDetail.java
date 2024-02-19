package com.shopping.ecommerce.model;

import javax.persistence.*;

@Entity

public class OrderDetail
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	@OneToOne
	@JoinColumn(name = "product")
	Product product;

	int totalQuantity;



	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetail(int orderId, Customer customer, Product product, int totalQuantity) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.product = product;
		this.totalQuantity = totalQuantity;

	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	
}
