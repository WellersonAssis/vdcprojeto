package com.example.vdcprojeto.service;

import com.example.vdcprojeto.exception.RegraNegocioException;
import com.example.vdcprojeto.model.entity.*;
import com.example.vdcprojeto.model.repository.CarroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarroService {

    private CarroRepository repository;
    private List<Cliente> clientes;
    private List<Marca> marcas;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public List<Carro> getCarros(){
        return repository.findAll();
    }

    public Optional<Carro> getCarrosById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Carro salvar(Carro carro) {
        validar(carro);
        return repository.save(carro);
    }

    @Transactional
    public void excluir(Carro carro) {
        Objects.requireNonNull(carro.getId());
        repository.delete(carro);

    }

    public void validar(Carro carro) {
        if (carro.getNome() == null || carro.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }
}
