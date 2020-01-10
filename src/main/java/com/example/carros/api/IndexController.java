package com.example.carros.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String get() {
        return "GET Spring Boot";
    }

    @PostMapping()
    public String post() {
        return "POST Spring Boot";
    }

    @PutMapping()
    public String put() {
        return "PUT Spring Boot";
    }

    @DeleteMapping()
    public String delete() {
        return "DELETE Spring Boot";
    }
}
