package com.example.vdcprojeto.api.controller;
import com.example.vdcprojeto.api.dto.CarroDTO;

import com.example.vdcprojeto.exception.RegraNegocioException;
import com.example.vdcprojeto.model.entity.Carro;
import com.example.vdcprojeto.model.entity.Cliente;
import com.example.vdcprojeto.model.entity.Venda;
import com.example.vdcprojeto.model.entity.Marca;
import com.example.vdcprojeto.service.ClienteService;
import com.example.vdcprojeto.service.MarcaService;
import com.example.vdcprojeto.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/carros")
@RequiredArgsConstructor

public class CarroController {

    private final ClienteService service;
    private final VendaService vendaService;
    private final MarcaService marcaService;

    @GetMapping ()
    public ResponseEntity get() {
        List<Carro> carros = service.getCarros();
        return ResponseEntity.ok(carros.stream().map(CarroDTO::create).collect(Collectors.toList()));
    }

}
