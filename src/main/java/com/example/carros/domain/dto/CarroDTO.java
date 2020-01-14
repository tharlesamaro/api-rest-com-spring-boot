package com.example.carros.domain.dto;

import com.example.carros.domain.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarroDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String descricao;
    private String url_foto;
    private String url_video;
    private String latitude;
    private String longitude;

    public static CarroDTO create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }
}
