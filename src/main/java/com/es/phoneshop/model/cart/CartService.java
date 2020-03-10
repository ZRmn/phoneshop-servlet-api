package com.es.phoneshop.model.cart;

import javax.servlet.http.HttpSession;

public interface CartService {

    Cart getCart(HttpSession session);

    void add(HttpSession session, Long productId, int quantity) throws OutOfStockException;
}
