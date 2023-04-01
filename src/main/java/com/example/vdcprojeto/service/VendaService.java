package com.example.vdcprojeto.service;

import com.example.vdcprojeto.exception.RegraNegocioException;
import com.example.vdcprojeto.model.entity.Venda;
import com.example.vdcprojeto.model.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendaService {

    private VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public List<Venda> getVendas() {
        return repository.findAll();
    }

    public Optional<Venda> getVendaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Venda salvar(Venda venda) {
        validar(venda);
        return repository.save(venda);
    }

    @Transactional
    public void excluir(Venda venda) {
        Objects.requireNonNull(venda.getId());
        repository.delete(venda);
    }

    public void validar(Venda venda) {
        if (venda.getValor() == null || venda.getValor().equals("")) {
            throw new RegraNegocioException("Noma Inválido");
        }
    }
}
