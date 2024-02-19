package com.shopping.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private float productPrice;
    private String productType;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Product(int productId,  String productName,
                   float productPrice, String productType) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
    }

    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }


}

