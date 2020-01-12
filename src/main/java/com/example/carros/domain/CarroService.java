package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<CarroDTO> getCarros() {
        return repository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public Optional<CarroDTO> getCarroById(Long id) {
        return repository.findById(id).map(CarroDTO::create);
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return repository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public Carro insert(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o registro");

        return repository.save(carro);
    }

    public void delete(Long id) {
        Assert.notNull(id, "Não foi possível deletar o registro");

        if (getCarroById(id).isPresent()) {
            repository.deleteById(id);
        }
    }
}
