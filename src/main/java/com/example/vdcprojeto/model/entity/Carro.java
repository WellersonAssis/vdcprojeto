package com.example.vdcprojeto.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Carro {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer codProduto;
    private Float valor;




    @ManyToOne
    private Venda venda;

    @ManyToOne
    private Marca marca;
}
