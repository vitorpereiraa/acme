package com.isep.acme.model;

import com.isep.acme.services.iServices.SkuGeneratorService;

public class SkuGeneratorMethod2 implements SkuGeneratorService {
    @Override
    public String generateSku(String value) {
        int hashCode = value.hashCode();
        String hashCodeString = Integer.toString(hashCode);
        if (hashCodeString.length() <= 10) {
            return Integer.toHexString(hashCode);
        }

        return Utils.GetMiddleOfString(hashCodeString);
    }
}
