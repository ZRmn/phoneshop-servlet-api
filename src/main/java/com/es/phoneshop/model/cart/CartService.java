package com.es.phoneshop.model.cart;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public interface CartService {

    Cart getCart(HttpSession session);

    void add(Cart cart, Long productId, int quantity) throws OutOfStockException;

    void update(Cart cart, Long productId, int quantity) throws OutOfStockException;

    void delete(Cart cart, Long productId);

    int calculateTotalQuantity(Cart cart);

    BigDecimal calculateTotalPrice(Cart cart);
}
