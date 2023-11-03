package com.isep.acme.model;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.isep.acme.services.iServices.SkuGeneratorService;

@Component
public class SkuGeneratorMethod1 implements SkuGeneratorService {

    private static final Random random = new Random();
    private static final String supportedSymbols = "!\"#$%&'()*+,-./:;<=>?@[]\\^_~`{}|";
    private static final String supportedLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * pattern 0A0A0A-0A0A-0A
     * 0 being 0-9
     * A being A-Z
     * A special character or symbol is added to the end
     */
    @Override
    public String generateSku(String value) {
        return new StringBuilder()
                .append(generateRandomNumbersAndLettersAlternatly(4))
                .append("-")
                .append(generateRandomNumbersAndLettersAlternatly(3))
                .append("-")
                .append(generateRandomNumbersAndLettersAlternatly(2))
                .append(supportedSymbols.charAt(random.nextInt(supportedSymbols.length())))
                .toString();
    }

    private String generateRandomNumbersAndLettersAlternatly(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                sb.append(random.nextInt(10));
            } else {
                sb.append(supportedLetters.charAt(random.nextInt(supportedLetters.length())));
            }
        }
        return sb.toString();
    }
}
