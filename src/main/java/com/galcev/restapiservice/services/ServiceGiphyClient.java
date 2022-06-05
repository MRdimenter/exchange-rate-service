package com.galcev.restapiservice.services;


import com.galcev.restapiservice.domains.giphy.Giphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "giphy", url = "${giphy.url}")
public interface ServiceGiphyClient {
    @GetMapping(value = "?api_key=" + "${giphy.api_key}" + "&tag=" + "{topic}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Giphy getRandomGifBySearch(@PathVariable("topic") String search);
}
