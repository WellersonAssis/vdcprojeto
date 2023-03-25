package com.example.vdcprojeto.api.dto;

import com.example.vdcprojeto.model.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class MarcaDTO {
        private Long id;

        private String tipoDeCarro;
        private String fabricante;
        private String anoFabricacao;

        public static MarcaDTO create(Marca marca){
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(marca, MarcaDTO.class);
        }


    }


