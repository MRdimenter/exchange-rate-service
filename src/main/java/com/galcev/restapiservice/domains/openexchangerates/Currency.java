package com.galcev.restapiservice.domains.openexchangerates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@Data
public class Currency {
    @JsonProperty("base")
    private String base;
    @JsonProperty("rates")
    private Map<String, BigDecimal> rates;

}
