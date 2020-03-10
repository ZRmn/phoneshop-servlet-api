package com.es.phoneshop.model.cart;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public interface CartService {

    Cart getCart(HttpSession session);

    void add(HttpSession session, Long productId, int quantity) throws OutOfStockException;

    void update(HttpSession session, Long productId, int quantity) throws OutOfStockException;

    void delete(HttpSession session, Long productId);

    int calculateTotalQuantity(HttpSession session);

    BigDecimal calculateTotalPrice(HttpSession session);
}
