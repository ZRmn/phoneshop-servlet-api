package com.es.phoneshop.model.order;

import com.es.phoneshop.model.cart.CartItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order {

    private Long id;
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;
    private BigDecimal deliveryCosts;
    private CustomerData customerData;

    public Order() {
    }

    public Order(Long id, List<CartItem> cartItems, BigDecimal totalPrice, BigDecimal deliveryCosts, CustomerData customerData) {
        this.id = id;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.deliveryCosts = deliveryCosts;
        this.customerData = customerData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDeliveryCosts() {
        return deliveryCosts;
    }

    public void setDeliveryCosts(BigDecimal deliveryCosts) {
        this.deliveryCosts = deliveryCosts;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public enum PaymentMethod {
        CASH,
        CARD
    }

    public static class CustomerData {

        private String fullName;
        private String phoneNumber;
        private LocalDate deliveryDate;
        private String address;
        private PaymentMethod paymentMethod;

        public CustomerData() {
        }

        public CustomerData(String fullName, String phoneNumber, LocalDate deliveryDate, String address, PaymentMethod paymentMethod) {
            this.fullName = fullName;
            this.phoneNumber = phoneNumber;
            this.deliveryDate = deliveryDate;
            this.address = address;
            this.paymentMethod = paymentMethod;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public LocalDate getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(LocalDate deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public PaymentMethod getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
    }
}
