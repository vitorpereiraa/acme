package com.isep.acme.services;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl2 implements TestService{
    @Override
    public String GetTest() {
        return "TESTE 222";
    }
}
