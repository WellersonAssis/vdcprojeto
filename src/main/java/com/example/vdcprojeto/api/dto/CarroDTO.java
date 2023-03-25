package com.example.vdcprojeto.api.dto;

import com.example.vdcprojeto.model.entity.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CarroDTO {

        private Long id;


        private String nome;
        private Integer codProduto;
        private Float valor;

        public static CarroDTO create(Carro carro) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(carro, CarroDTO.class);
        }
    }

