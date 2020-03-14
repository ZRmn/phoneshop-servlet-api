package com.es.phoneshop.model.order;

public class OrderLink {

    private Long id;
    private Long orderId;
    private String link;

    public OrderLink() {
    }

    public OrderLink(Long id, Long orderId, String link) {
        this.id = id;
        this.orderId = orderId;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
