package com.shopping.ecommerce.service;

import com.shopping.ecommerce.Exceptions.InvalidProductIdException;
import com.shopping.ecommerce.model.Product;
import com.shopping.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository Pr;

	@Override
	public List<Product> getAllProducts()
	{
		return Pr.findAll();
	}
	public Product saveProduct(Product product) {
		return Pr.save(product);
	}
//
	@Override
	public Product findOneProduct(int productId) throws InvalidProductIdException
	{
		Product product = Pr.findById( productId).orElse(null);
		if (product.equals(null))
		{
			throw new InvalidProductIdException(" Product not found for id :: " + productId);
			
		}

		return product;
	}

	@Override
	public List<Product> getProductsByType(String productType) 
	{
		return Pr.getProductsByType(productType);
		
	}

	@Override
	public List<String> getDistinctProduct() {
		return Pr.getDistinctProducts();
	}

	@Override
	public List<Product> getAllProductsByType() 
	{
		return Pr.getAllProductsByType();
	}

	@Override
	public List<Product> getAllProductsByPrice() {
		return Pr.getAllProductsByPrice();
		
	}
	@Override
	public List<Product>findByproductType(String productType)
	{
		return Pr.findByproductType(productType);
	}
	@Override
	public List<Product>findByproductName(String productName)
	{
		return Pr.findByproductName(productName);
	}

}
