package com.galcev.restapiservice.controllers;

import com.galcev.restapiservice.model.RateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/currency")
public class RateController {
    private final RateResult rateResult;
    @Value("${spring_error.BAD_REQUEST_STATUS}")
    private String BAD_REQUEST_STATUS;
    @Value("${spring_error.gif_error.BAD_REQUEST_STATUS_GIF}")
    private String BAD_REQUEST_STATUS_GIF;

    public RateController(RateResult rateResult) {
        this.rateResult = rateResult;
    }


    @GetMapping("gif/{currencyCode}")
    public String rate(@PathVariable String currencyCode, Model model) {
        String response = rateResult.courseChange(currencyCode);

        if (response.equals(BAD_REQUEST_STATUS)) {
            model.addAttribute("URL", BAD_REQUEST_STATUS_GIF);
        } else model.addAttribute("URL", response);

        return "currency";
    }

}
