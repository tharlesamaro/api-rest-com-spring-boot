package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.dto.CarroDTO;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosApiTests {
    @Autowired
    protected TestRestTemplate rest;

    private ResponseEntity<CarroDTO> getCarro(String url) {
        return rest.withBasicAuth("tharles", "123").getForEntity(url, CarroDTO.class);
    }

    private ResponseEntity<List<CarroDTO>> getCarros(String url) {
        return rest.withBasicAuth("tharles", "123").exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDTO>>() {
                });
    }

    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        // Insert
        ResponseEntity response = rest.withBasicAuth("admin", "123").postForEntity("/api/v1/carros", carro, null);
        System.out.println(response);

        // VErifica se criou
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        CarroDTO c = getCarro(location).getBody();

        Assertions.assertNotNull(c);
        Assertions.assertEquals("Porshe", c.getNome());
        Assertions.assertEquals("esportivos", c.getTipo());

        // Deletar o objeto
        rest.withBasicAuth("admin", "123").delete(location);

        // Verificar se deletou
        Assertions.assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());

    }

    @Test
    public void testLista() {
        List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
        Assertions.assertNotNull(carros);
        Assertions.assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        Assertions.assertEquals(10, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
        Assertions.assertEquals(10, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
        Assertions.assertEquals(10, getCarros("/api/v1/carros/tipo/luxo").getBody().size());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {

        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        CarroDTO c = response.getBody();
        Assertions.assertEquals("Ferrari FF", c.getNome());
    }

    @Test
    public void testGetNotFound() {

        ResponseEntity response = getCarro("/api/v1/carros/1100");
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
