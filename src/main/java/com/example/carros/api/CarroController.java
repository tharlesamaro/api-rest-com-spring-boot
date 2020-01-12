package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
    @Autowired
    private CarroService service;

    @GetMapping()
    public Iterable<Carro> get() {

        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> get(@PathVariable("id") Long id) {

        return service.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) {

        return service.getCarrosByTipo(tipo);
    }

    @PostMapping
    public String post(@RequestBody Carro carro) {

        Carro car = service.insert(carro);

        return "Carro salvo com sucesso. Id: " + car.getId();
    }

    @PutMapping("/{id}")
    public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {

        Carro car = service.update(carro, id);

        return "Carro atualizado com sucesso. Carro ID: " + car.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {

        service.delete(id);

        return "Carro deletado com sucesso";

    }

}
