package com.isep.acme.model;

import com.isep.acme.services.SkuGenerator;
import org.springframework.stereotype.Component;

@Component
public class SkuGeneratorMethod2 implements SkuGenerator {
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
