package com.example.vdcprojeto.api.controller;




import com.example.vdcprojeto.api.dto.VendaDTO;
import com.example.vdcprojeto.model.entity.Venda;
import com.example.vdcprojeto.service.CarroService;
import com.example.vdcprojeto.service.FuncionarioService;
import com.example.vdcprojeto.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService service;
    private final CarroService carroService;

    @GetMapping()
    public ResponseEntity get() {
        List<Venda> vendas = service.getVendas();
        return ResponseEntity.ok(vendas.stream().map(VendaDTO::create).collect(Collectors.toList()));
    }
}
