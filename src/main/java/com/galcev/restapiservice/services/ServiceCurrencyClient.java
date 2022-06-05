package com.galcev.restapiservice.services;

import com.galcev.restapiservice.domains.openexchangerates.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "openexchangerates", url = "${openexchangerates.url}")
public interface ServiceCurrencyClient {

    @GetMapping("/historical/" + "{date}" + ".json?app_id=" + "${openexchangerates.api_key}" )
    Currency getHistoricalCurrency(@PathVariable String date);


    @GetMapping("/latest.json?" + "app_id=" + "${openexchangerates.api_key}")
    Currency getLatestCurrency();
}

