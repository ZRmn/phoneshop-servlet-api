package com.es.phoneshop.model.order;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.model.cart.HttpSessionCartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class DefaultOrderService implements OrderService {

    private static DefaultOrderService instance;

    private DefaultOrderService() {

    }

    public static synchronized DefaultOrderService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderService();
        }

        return instance;
    }

    private CartService cartService = HttpSessionCartService.getInstance();
    private ArrayListOrderDao orderDao = ArrayListOrderDao.getInstance();
    private ArrayListOrderLinkDao orderLinkDao = ArrayListOrderLinkDao.getInstance();

    private final BigDecimal DELIVERY_COSTS = BigDecimal.valueOf(5);

    @Override
    public Order placeOrder(Cart cart, Order.CustomerData customerData) {
        Order order = new Order();
        order.setCartItems(new ArrayList<>(cart.getCartItems()));
        order.setTotalPrice(cartService.calculateTotalPrice(cart));
        order.setCustomerData(customerData);
        order.setDeliveryCosts(calculateDeliveryCosts(cart));

        orderDao.save(order);
        cart.getCartItems().clear();

        UUID uuid = UUID.randomUUID();
        String link = uuid.toString();
        OrderLink orderLink = new OrderLink();
        orderLink.setOrderId(order.getId());
        orderLink.setLink(link);

        orderLinkDao.save(orderLink);

        return order;
    }

    @Override
    public BigDecimal calculateDeliveryCosts(Cart cart) {
        return DELIVERY_COSTS;
    }

    @Override
    public String getOrderLink(Order order) {
        return orderLinkDao.getOrderLink(order.getId());
    }

    @Override
    public Order getOrderByLink(String link) {
        Long orderId = orderLinkDao.getOrderId(link);
        return orderDao.get(orderId);
    }
}
