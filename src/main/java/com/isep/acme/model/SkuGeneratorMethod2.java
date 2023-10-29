package com.isep.acme.model;

import org.springframework.stereotype.Component;

import com.isep.acme.services.iServices.SkuGeneratorService;

@Component
public class SkuGeneratorMethod2 implements SkuGeneratorService {
    @Override
    public String generateSku() {
        String description = "aaashbdajshbdasbdjhasba";
        int hashcode = description.hashCode();
        String hex = Integer.toHexString(hashcode);

        if (hex.length() <= 10) {
            return hex;
        }

        int middlePosition = findStringMiddlePosition(hex);
        return hex.substring(middlePosition - 5, middlePosition + 5);
    }

    private int findStringMiddlePosition(String str) {
        int position;

        if (str.length() % 2 == 1) {
            position = str.length() / 2;
        } else {
            position = str.length() / 2 - 1;
        }

        return position;
    }
}
