package com.shopping.ecommerce.controller;

import com.shopping.ecommerce.Exceptions.InvalidProductIdException;
import com.shopping.ecommerce.Exceptions.InvalidProductNameException;
import com.shopping.ecommerce.Exceptions.InvalidProductTypeException;
import com.shopping.ecommerce.model.Product;
import com.shopping.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

	@Autowired
	private ProductService productservice;
	@GetMapping("/products")
	public List<Product> retrieveAllProducts()
	{
	
	return productservice.getAllProducts();
	}
	@PostMapping("/product")
	public Product addProducts(@RequestBody Product product)
	{
	
	return productservice.saveProduct(product);
	}
	
	//retrieves a specific user detail
	@GetMapping("/product/{productid}")
	public Product retriveProduct(@PathVariable int productid) throws InvalidProductIdException
	{
		return productservice.findOneProduct(productid);
	}

	@GetMapping("/product1/{productType}")
	public List<Product>findByproductType(@PathVariable String productType)throws InvalidProductTypeException
	{
		return productservice.findByproductType(productType);
	}
	@GetMapping("/product2/{productName}")
	public List<Product>findByproductName(@PathVariable String productName)throws InvalidProductNameException
	{
		return productservice.findByproductName(productName);
	}
	
	@GetMapping("/products/distinctProducts")
	public List<String> getDistinctProduct()
	{
		return productservice.getDistinctProduct();
	}
	
	@GetMapping("/products/productsByType")
	public List<Product> getAllProductsByType()
	{
		return productservice.getAllProductsByType();
	}
	
	@GetMapping("/products/productsByPrice")
	public List<Product> getAllProductsByPrice()
	{
		return productservice.getAllProductsByPrice();
	}
	
	
}

