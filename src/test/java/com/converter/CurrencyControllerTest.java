package com.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService service;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void convertShouldReturnResponseFromService() throws Exception {
        ExchangeResponse exchangeResponse = new ExchangeResponse("123", "1.2");
        when(service.get(new Currency("PLN"), new Currency("EUR"), "100.00")).thenReturn(exchangeResponse);

        Map<String, Object> payload = new HashMap<>();
        payload.put("currencyFrom", "PLN");
        payload.put("currencyTo", "EUR");
        payload.put("amount", "100");

        this.mockMvc.perform(post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
