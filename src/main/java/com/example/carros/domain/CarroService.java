package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;

public class CarroService {
    public List<Carro> getCarros() {
        List<Carro> carros = new ArrayList<>();

        carros.add(new Carro(1L, "Fusca"));
        carros.add(new Carro(1L, "Brasilia"));
        carros.add(new Carro(1L, "Chevete"));

        return carros;
    }
}
