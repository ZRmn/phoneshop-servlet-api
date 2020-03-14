package com.es.phoneshop.model.order;

import com.es.phoneshop.model.dao.AbstractArrayListDao;

import java.util.NoSuchElementException;

public class ArrayListOrderLinkDao extends AbstractArrayListDao<OrderLink> {

    private static ArrayListOrderLinkDao instance;

    private ArrayListOrderLinkDao() {

    }

    public static synchronized ArrayListOrderLinkDao getInstance() {
        if (instance == null) {
            instance = new ArrayListOrderLinkDao();
        }

        return instance;
    }

    public Long getOrderId(String link) {
        OrderLink orderLink = items.stream()
                .filter(ol -> ol.getLink().equals(link))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        return orderLink.getOrderId();
    }

    public String getOrderLink(Long orderId) {
        OrderLink orderLink = items.stream()
                .filter(ol -> ol.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        return orderLink.getLink();
    }

    @Override
    protected Long getId(OrderLink item) {
        return item.getId();
    }

    @Override
    protected void setId(OrderLink item, Long id) {
        item.setId(id);
    }

    @Override
    protected void updateItem(OrderLink item, OrderLink update) {

    }
}
