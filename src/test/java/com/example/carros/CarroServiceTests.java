package com.example.carros;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class CarroServiceTests {

    @Autowired
    private CarroService service;

    @Test
    public void testInserirCarro() {

        Carro carro = new Carro();
        carro.setNome("Ferrari Teste");
        carro.setTipo("esportivos");

        CarroDTO carroDTO = service.insert(carro);
        Assertions.assertNotNull(carroDTO);

        Long id = carroDTO.getId();
        Assertions.assertNotNull(id);

        CarroDTO c = service.getCarroById(id);
        Assertions.assertNotNull(c);

        Assertions.assertEquals("Ferrari Teste", carroDTO.getNome());
        Assertions.assertEquals("esportivos", carroDTO.getTipo());

        service.delete(id);

        try {
            Assertions.assertNull(service.getCarroById(id));
            fail("O carro não foi excluiído");
        } catch (ObjectNotFoundException e) {
            // OK
        }

    }

    @Test
    public void testLista() {

		List<CarroDTO> carros = service.getCarros();

		Assertions.assertEquals(30, carros.size());
	}

    @Test
    public void testListaPorTipo() {

        Assertions.assertEquals(10, service.getCarrosByTipo("classicos").size());
        Assertions.assertEquals(10, service.getCarrosByTipo("esportivos").size());
        Assertions.assertEquals(10, service.getCarrosByTipo("luxo").size());
        Assertions.assertEquals(0, service.getCarrosByTipo("x").size());
    }

    @Test
    public void testGet() {

        CarroDTO c = service.getCarroById(11L);

        Assertions.assertNotNull(c);

        Assertions.assertEquals("Ferrari FF", c.getNome());
    }

}
