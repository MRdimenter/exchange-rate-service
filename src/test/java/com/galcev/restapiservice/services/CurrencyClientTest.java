package com.galcev.restapiservice.services;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyClientTest {
    @Autowired
    ServiceCurrencyClient serviceCurrencyClient;


    /**
     * HttpClientHistoryCurrency test
     * Result: response from server: 200 / OK
     */
    @Test
    void testHttpClientHistoryCurrency() {
        assertNotNull(serviceCurrencyClient.getHistoricalCurrency("2022-01-01")
                .getRates()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals("RUB"))
                .findFirst().get());
    }


    /**
     * HttpClientLatestCurrency test
     * Result: response from server: 200 / OK
     */
    @Test
    void testHttpClientLatestCurrency() {
        assertNotNull(serviceCurrencyClient.getLatestCurrency()
                .getRates()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals("RUB"))
                .findFirst().get());
    }

}
