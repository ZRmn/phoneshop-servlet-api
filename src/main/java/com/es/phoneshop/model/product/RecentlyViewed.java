package com.es.phoneshop.model.product;

import java.util.ArrayDeque;
import java.util.Deque;

public class RecentlyViewed {

    private Deque<Product> products;

    public RecentlyViewed() {
        products = new ArrayDeque<>();
    }

    public Deque<Product> getProducts() {
        return products;
    }

    public void setProducts(Deque<Product> products) {
        this.products = products;
    }
}
