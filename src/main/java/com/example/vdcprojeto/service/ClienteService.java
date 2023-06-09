package com.example.vdcprojeto.service;

import com.example.vdcprojeto.exception.RegraNegocioException;
import com.example.vdcprojeto.model.entity.Carro;
import com.example.vdcprojeto.model.entity.Cliente;
import com.example.vdcprojeto.model.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> getCliente() {
        return repository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        validar(cliente);
        return  repository.save(cliente);
    }

    @Transactional
    public void excluir(Cliente cliente) {
        Objects.requireNonNull(cliente.getId());
        repository.delete(cliente);
    }

    public void validar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome Inválido");
        }
    }

    public List<Carro> getCarros() {
        return getCarros();
    }

    public Optional<Carro> getCarrosById(Long id) {
        return null;
    }

}
