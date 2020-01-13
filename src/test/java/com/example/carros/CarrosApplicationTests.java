package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

@SpringBootTest
class CarrosApplicationTests {

    @Autowired
    private CarroService service;

    @Test
    public void inserirCarro() {

        Carro carro = new Carro();
        carro.setNome("Ferrari Teste");
        carro.setTipo("esportivos");

        CarroDTO carroDTO = service.insert(carro);
        Assertions.assertNotNull(carroDTO);

        Long id = carroDTO.getId();
        Assertions.assertNotNull(id);

        Optional<CarroDTO> optionalCarroDTO = service.getCarroById(id);
        Assertions.assertTrue(optionalCarroDTO.isPresent());

        carroDTO = optionalCarroDTO.get();
        Assertions.assertEquals("Ferrari Teste", carroDTO.getNome());
        Assertions.assertEquals("esportivos", carroDTO.getTipo());

        service.delete(id);
        Assertions.assertFalse(service.getCarroById(id).isPresent());
    }

    @Test
    void contextLoads() {
    }

}
