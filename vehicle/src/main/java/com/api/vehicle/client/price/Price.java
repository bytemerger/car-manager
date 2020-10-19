package com.api.vehicle.client.price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
