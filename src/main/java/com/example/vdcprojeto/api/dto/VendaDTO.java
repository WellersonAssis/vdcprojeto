package com.example.vdcprojeto.api.dto;

import com.example.vdcprojeto.model.entity.Venda;
import com.example.vdcprojeto.model.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class VendaDTO {
        private Long id;
        private String vendedor;
        private Float valor;

        public static VendaDTO create(Venda venda) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(venda, VendaDTO.class);


        }

}



