package com.example.vdcprojeto.api.dto;

import com.example.vdcprojeto.model.entity.Cliente;
import com.example.vdcprojeto.model.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {


    private Long id;

    private Integer codCliente;

    public static ClienteDTO create(Cliente cliente) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cliente, ClienteDTO.class);


    }
}