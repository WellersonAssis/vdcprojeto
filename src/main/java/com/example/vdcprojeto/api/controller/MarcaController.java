package com.example.vdcprojeto.api.controller;


import com.example.vdcprojeto.api.dto.MarcaDTO;
import com.example.vdcprojeto.model.entity.Marca;
import com.example.vdcprojeto.service.CarroService;
import com.example.vdcprojeto.service.FuncionarioService;
import com.example.vdcprojeto.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/vendas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService service;
    private final CarroService carroService;

    @GetMapping()
    public ResponseEntity get() {
        List<Marca> marcas = service.getMarcas();
        return ResponseEntity.ok(marcas.stream().map(MarcaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Marca> marca = service.getMarcaById(id);
        if (!marca.isPresent()) {
            return new ResponseEntity("Marca não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(marca.map(MarcaDTO::create));
    }
}
