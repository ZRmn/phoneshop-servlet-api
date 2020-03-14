package com.es.phoneshop.model.order;

import com.es.phoneshop.model.dao.AbstractArrayListDao;

public class ArrayListOrderDao extends AbstractArrayListDao<Order> {

    private static ArrayListOrderDao instance;

    private ArrayListOrderDao() {

    }

    public static synchronized ArrayListOrderDao getInstance() {
        if (instance == null) {
            instance = new ArrayListOrderDao();
        }

        return instance;
    }

    @Override
    protected Long getId(Order item) {
        return item.getId();
    }

    @Override
    protected void setId(Order item, Long id) {
        item.setId(id);
    }

    @Override
    protected void updateItem(Order item, Order update) {
        item.setCartItems(update.getCartItems());
        item.setDeliveryCosts(update.getDeliveryCosts());
        item.setTotalPrice(update.getTotalPrice());
        item.getCustomerData().setAddress(update.getCustomerData().getAddress());
        item.getCustomerData().setDeliveryDate(update.getCustomerData().getDeliveryDate());
        item.getCustomerData().setFullName(update.getCustomerData().getFullName());
        item.getCustomerData().setPaymentMethod(update.getCustomerData().getPaymentMethod());
        item.getCustomerData().setPhoneNumber(update.getCustomerData().getPhoneNumber());
    }
}
