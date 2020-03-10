package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.product.Product;

public class OutOfStockException extends RuntimeException {

    private Product product;
    private int requiredQuantity;

    public OutOfStockException(Product product, int requiredQuantity) {
        this.product = product;
        this.requiredQuantity = requiredQuantity;
    }
}
