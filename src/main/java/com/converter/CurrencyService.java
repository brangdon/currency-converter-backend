package com.converter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    public ExchangeResponse get(Currency currencyFrom, Currency currencyTo, String amount) {
        RestTemplate restTemplate = new RestTemplate();

        String symbolFrom = currencyFrom.getSymbol();
        String symbolTo = currencyTo.getSymbol();

        ExchangeRequest exchangeRequest = restTemplate.getForObject("http://api.fixer.io/latest?base=" + symbolFrom, ExchangeRequest.class);

        double rate = Double.parseDouble(exchangeRequest.getRates().get(symbolTo));
        double value = Double.parseDouble(amount) * rate;

        String result = String.format("%.2f", value);

        return new ExchangeResponse(result, String.valueOf(rate));
    }
}
