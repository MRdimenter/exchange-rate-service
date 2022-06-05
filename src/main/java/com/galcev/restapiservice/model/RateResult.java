package com.galcev.restapiservice.model;

import com.galcev.restapiservice.services.ServiceCurrencyClient;
import com.galcev.restapiservice.services.ServiceGiphyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class RateResult {
    @Value("${time.timezone}")
    private String timezone;
    @Value("${time.date_format}")
    private String dateFormat;
    @Value("#{'${giphy.tags}'.split(',')}")
    private List<String> giphyTags;
    @Value("${spring_error.BAD_REQUEST_STATUS}")
    private String ERROR;


    private final ServiceCurrencyClient serviceCurrencyClient;
    private final ServiceGiphyClient serviceGiphyClient;


    public RateResult(ServiceCurrencyClient serviceCurrencyClient, ServiceGiphyClient serviceGiphyClient) {
        this.serviceCurrencyClient = serviceCurrencyClient;
        this.serviceGiphyClient = serviceGiphyClient;
    }

    public String courseChange(String currencyCode) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat).withZone(ZoneId.of(timezone));

        ZonedDateTime today = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(timezone));
        ZonedDateTime yesterday = today.minus(1, ChronoUnit.DAYS);


        Optional<BigDecimal> todayExchangeRate = getRateByDate("", currencyCode);
        Optional<BigDecimal> yesterdayExchangeRate = getRateByDate(dateTimeFormatter.format(yesterday), currencyCode);


        if (todayExchangeRate.isPresent() && yesterdayExchangeRate.isPresent()) {
            log.info("Today's course: " + todayExchangeRate.get() + "\nYesterday's course: " + yesterdayExchangeRate.get());
            return todayExchangeRate.get().compareTo(yesterdayExchangeRate.get()) >= 0 ? getRandomGifBySearch(giphyTags.get(0)) : getRandomGifBySearch(giphyTags.get(1));
        } else {
            log.error(ERROR);
            return ERROR;
        }

    }


    private Optional<BigDecimal> getRateByDate(String date, String currencyCode) {
        Map<String, BigDecimal> serviceCurrency;
        if (date.isEmpty()) {
            serviceCurrency = serviceCurrencyClient
                    .getLatestCurrency()
                    .getRates();

        } else {
            serviceCurrency = serviceCurrencyClient
                    .getHistoricalCurrency(date)
                    .getRates();
        }

        return serviceCurrency
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(currencyCode))
                .map(Map.Entry::getValue).findFirst();
    }

    private String getRandomGifBySearch(String topic) {
        return serviceGiphyClient.getRandomGifBySearch(topic).getData().getImages().getOriginal().get("url");
    }

}
