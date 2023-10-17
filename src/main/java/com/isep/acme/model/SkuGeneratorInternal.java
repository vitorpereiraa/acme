package com.isep.acme.model;

import com.isep.acme.services.SkuGenerator;
import org.springframework.stereotype.Component;

@Component
public class SkuGeneratorInternal implements SkuGenerator {
    @Override
    public String generateSku() {
        return "aksdmkasm";
    }
}
