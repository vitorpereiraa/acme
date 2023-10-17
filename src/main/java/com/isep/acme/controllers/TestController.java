package com.isep.acme.controllers;

import com.isep.acme.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
//@RefreshScope
public class TestController {

    @Autowired
    @Qualifier("testService")
    private TestService service;

    @GetMapping
    public ResponseEntity<String> getTest() {
        final var body = service.GetTest();

        return ResponseEntity.ok().body(body);
    }
}
