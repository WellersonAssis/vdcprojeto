package com.example.vdcprojeto.api.controller;


import com.example.vdcprojeto.api.dto.MarcaDTO;
import com.example.vdcprojeto.exception.RegraNegocioException;
import com.example.vdcprojeto.model.entity.Marca;
import com.example.vdcprojeto.service.CarroService;
import com.example.vdcprojeto.service.FuncionarioService;
import com.example.vdcprojeto.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity post(MarcaDTO dto) {
        try {
            Marca marca = converter(dto);
            marca = service.salvar(marca);
            return new ResponseEntity(marca, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, MarcaDTO dto) {
        if (!service.getMarcaById(id).isPresent()) {
            return new ResponseEntity("Marca não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Marca marca = converter(dto);
            marca.setId(id);
            service.salvar(marca);
            return ResponseEntity.ok(marca);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Marca> marca = service.getMarcaById(id);
        if (!marca.isPresent()) {
            return new ResponseEntity("Marca não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(marca.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Marca converter(MarcaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Marca marca = modelMapper.map(dto, Marca.class);
        return marca;
    }


}
