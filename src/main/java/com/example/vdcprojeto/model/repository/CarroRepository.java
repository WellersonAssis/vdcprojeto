package com.example.vdcprojeto.model.repository;

import com.example.vdcprojeto.model.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
