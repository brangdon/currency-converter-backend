package com.converter;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(
            value = "/convert",
            method = RequestMethod.POST)
    public ExchangeResponse convert(@RequestBody Map<String, Object> payload)
            throws Exception {

        String symbolFrom = (String) payload.get("currencyFrom");
        String symbolTo = (String) payload.get("currencyTo");
        String amount = (String.valueOf(payload.get("amount")));

        return currencyService.get(new Currency(symbolFrom), new Currency(symbolTo), amount);
    }
}