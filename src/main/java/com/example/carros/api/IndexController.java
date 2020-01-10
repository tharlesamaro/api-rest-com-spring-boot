package com.example.carros.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String get() {
        return "GET Spring Boot";
    }

    @GetMapping("/login/{login}/senha/{senha}")
    public String login(@PathVariable("login") String login, @PathVariable("senha") String senha) {
        return "Login: " + login + ", senha: " + senha;
    }

    @GetMapping("/carro/{id}")
    public String getCarroById(@PathVariable("id") Long id) {
        return "Carro " + id;
    }

    @GetMapping("/carro/tipo/{tipo}")
    public String getCarroByTipo(@PathVariable("tipo") String tipo) {
        return "Carros do tipo " + tipo;
    }
}
