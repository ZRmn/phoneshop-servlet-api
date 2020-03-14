package com.es.phoneshop.model.order;

import com.es.phoneshop.model.cart.Cart;

import java.math.BigDecimal;

public interface OrderService {

    Order placeOrder(Cart cart, Order.CustomerData customerData);

    BigDecimal calculateDeliveryCosts(Cart cart);

    String getOrderLink(Order order);

    Order getOrderByLink(String link);
}
