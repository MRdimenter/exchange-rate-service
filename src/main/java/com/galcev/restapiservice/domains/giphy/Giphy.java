package com.galcev.restapiservice.domains.giphy;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Data
public class Giphy {
    private Giphy data;
    private Images images;


    @Data
    public static class Images {
        private HashMap<String, String> original;

    }
}
