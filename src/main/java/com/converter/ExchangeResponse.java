package com.converter;

public class ExchangeResponse {
    private String value;
    private String rate;

    public ExchangeResponse(String value, String rate) {
        this.value = value;
        this.rate = rate;
    }

    public String getValue() {
        return value;
    }

    public String getRate() {
        return rate;
    }
}
