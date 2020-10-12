package com.services.pricing.data;

import java.math.BigDecimal;

public class Price {
    private Long id;
    private BigDecimal price;
    private String currency;

    public Price(){

    }
    public Price(Long id, BigDecimal price, String currency) {
        this.id = id;
        this.price = price;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
