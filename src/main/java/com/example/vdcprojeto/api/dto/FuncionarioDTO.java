package com.example.vdcprojeto.api.dto;

import com.example.vdcprojeto.model.entity.Funcionario;
import com.example.vdcprojeto.model.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class FuncionarioDTO {



        private Long id;

        private String funcao;
        private Float salario;
        private Integer codFuncionario;

        public static FuncionarioDTO create(Funcionario funcionario) {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(funcionario, FuncionarioDTO.class);
        }
}

