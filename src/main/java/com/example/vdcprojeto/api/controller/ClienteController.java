package com.example.vdcprojeto.api.controller;


import com.example.vdcprojeto.api.dto.ClienteDTO;
import com.example.vdcprojeto.model.entity.Cliente;
import com.example.vdcprojeto.service.CarroService;
import com.example.vdcprojeto.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;
    private final CarroService carroService;

    @GetMapping()
    public ResponseEntity get() {
        List<Cliente> clientes = service.getCliente();
        return ResponseEntity.ok(clientes.stream().map(ClienteDTO::create).collect(Collectors.toList()));
    }


}
