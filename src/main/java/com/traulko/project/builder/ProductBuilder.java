package com.traulko.project.builder;

import com.traulko.project.entity.Product;

import java.sql.Blob;

public class ProductBuilder {
    private Integer productId;
    private String title;
    private double price;
    private String description;
    private Blob image;

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Product getProduct() {
        return new Product(productId, title, price, description, image);
    }
}
