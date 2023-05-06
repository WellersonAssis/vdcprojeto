package com.example.vdcprojeto.api.controller;
import com.example.vdcprojeto.api.dto.CarroDTO;

import com.example.vdcprojeto.exception.RegraNegocioException;
import com.example.vdcprojeto.model.entity.Carro;
import com.example.vdcprojeto.model.entity.Cliente;
import com.example.vdcprojeto.model.entity.Venda;
import com.example.vdcprojeto.model.entity.Marca;
import com.example.vdcprojeto.service.CarroService;
import com.example.vdcprojeto.service.ClienteService;
import com.example.vdcprojeto.service.MarcaService;
import com.example.vdcprojeto.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/carros")
@RequiredArgsConstructor

public class CarroController {

    private final CarroService service;
    private final VendaService vendaService;
    private final MarcaService marcaService;

    @GetMapping()
    public ResponseEntity get() {
        List<Carro> carros = service.getCarros();
        return ResponseEntity.ok(carros.stream().map(CarroDTO::create).collect(Collectors.toList()));
    }


    @GetMapping("/{id}/")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Carro> carro = service.getCarrosById(id);
        if (!carro.isPresent()) {
            return new ResponseEntity("Carro não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(carro.map(CarroDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(CarroDTO dto) {
        try {
            Carro carro = converter(dto);
            Marca marca = marcaService.salvar(carro.getMarca());
            carro.setMarca(marca);
            carro = service.salvar(carro);
            return new ResponseEntity(carro, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, CarroDTO dto) {
        if (!service.getCarrosById(id).isPresent()) {
            return new ResponseEntity("Disciplina não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Carro carro = converter(dto);
            carro.setId(id);
            service.salvar(carro);
            return ResponseEntity.ok(carro);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Carro> carro = service.getCarrosById(id);
        if (!carro.isPresent()) {
            return new ResponseEntity("Carro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(carro.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public Carro converter(CarroDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Carro carro = modelMapper.map(dto, Carro.class);
        return carro;
    }
}