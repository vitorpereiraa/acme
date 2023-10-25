package com.isep.acme.httpRequests;

import com.isep.acme.services.HttpService;
import com.isep.acme.services.SkuHttpRequester;
import com.isep.acme.utils.Constants;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestSkuHttpRequester implements SkuHttpRequester {
    @Override
    public String getSku() {
        var httpservice = new HttpService();
        try {
            return httpservice.sendGetRequest("", String.class);
        } catch (IOException | InterruptedException e) {
            return Constants.EMPTY_STRING;
        }
    }
}
