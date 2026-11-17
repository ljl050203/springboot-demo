package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String greet(String name) {
        String target = (name == null || name.isBlank()) ? "World" : name.trim();
        return "Hello, " + target + "!";
    }
}
