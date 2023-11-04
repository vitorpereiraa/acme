package com.isep.acme.model;

import com.isep.acme.services.iServices.SkuGeneratorService;
import org.apache.commons.codec.digest.DigestUtils;

public class SkuGeneratorMethod2Alternative implements SkuGeneratorService {
    @Override
    public String generateSku(String value) {
        String hexStr = DigestUtils.sha256Hex(value);

        return Utils.GetMiddleOfString(hexStr);
    }
}
