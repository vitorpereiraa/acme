package com.isep.acme.services;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl1 implements TestService {
    @Override
    public String GetTest() {
        return "TEST!!";
    }
}
