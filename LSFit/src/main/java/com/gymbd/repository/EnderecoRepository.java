package com.gymbd.repository;

import com.gymbd.model.Endereco;
import com.gymbd.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    // Aqui você pode adicionar consultas personalizadas, se necessário
}
