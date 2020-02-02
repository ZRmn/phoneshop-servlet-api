package com.es.phoneshop.model.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public class Product {

    public static class PriceData {

        private BigDecimal price;
        private Currency currency;
        private LocalDate changeDate;

        public PriceData() {
        }

        public PriceData(BigDecimal price, Currency currency, LocalDate changeDate) {
            this.price = price;
            this.currency = currency;
            this.changeDate = changeDate;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        public LocalDate getChangeDate() {
            return changeDate;
        }

        public void setChangeDate(LocalDate changeDate) {
            this.changeDate = changeDate;
        }
    }

    private Long id;
    private String code;
    private String description;
    private int stock;
    private String imageUrl;
    private PriceData currentPriceData;
    private List<PriceData> priceHistory;

    public Product() {

    }

    public Product(Long id, String code, String description, int stock, String imageUrl, PriceData currentPriceData, List<PriceData> priceHistory) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.currentPriceData = currentPriceData;
        this.priceHistory = priceHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PriceData getCurrentPriceData() {
        return currentPriceData;
    }

    public void setCurrentPriceData(PriceData currentPriceData) {
        this.currentPriceData = currentPriceData;
    }

    public List<PriceData> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<PriceData> priceHistory) {
        this.priceHistory = priceHistory;
    }
}