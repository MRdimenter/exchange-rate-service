package com.galcev.restapiservice.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GiphyClientTest {
    @Autowired
    ServiceGiphyClient serviceGiphyClient;

    /**
     * HttpClientGiphy test
     * Result: response from server: 200 / OK
     */
    @Test
    void testHttpClientGiphy() {
        assertNotNull(serviceGiphyClient.getRandomGifBySearch("broke").getData().getImages().getOriginal().get("url"));
    }

}

