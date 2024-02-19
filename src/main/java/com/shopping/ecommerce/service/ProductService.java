package com.shopping.ecommerce.service;



import com.shopping.ecommerce.Exceptions.InvalidProductIdException;
import com.shopping.ecommerce.Exceptions.InvalidProductNameException;
import com.shopping.ecommerce.Exceptions.InvalidProductTypeException;
import com.shopping.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
	public Product saveProduct(Product product);
	public List<Product> getAllProducts();
//
	public Product findOneProduct(int productid) throws InvalidProductIdException;

	public List<Product> getProductsByType(String productType);

	public List<String> getDistinctProduct();

	public List<Product> getAllProductsByType();

	public List<Product> getAllProductsByPrice();
	public List<Product>findByproductType(String productType) throws InvalidProductTypeException;
	
	public List<Product>findByproductName(String productName) throws InvalidProductNameException;
}
