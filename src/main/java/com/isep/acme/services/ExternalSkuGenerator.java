package com.isep.acme.services;

import com.isep.acme.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ExternalSkuGenerator implements SkuGenerator{

    @Autowired
    @Qualifier("backupSkuGenerator")
    private SkuGenerator backupSkuGenerator;

    @Autowired
    private SkuHttpRequester httpRequester;

    @Override
    public String generateSku() {
        String sku = httpRequester.getSku();
        if(sku.equals(Constants.EMPTY_STRING)){
            // if external sku generator fails use backup internal generator
            return backupSkuGenerator.generateSku();
        }
        return sku;
    }
}
