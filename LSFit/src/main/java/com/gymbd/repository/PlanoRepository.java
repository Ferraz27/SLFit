package com.gymbd.repository;

import com.gymbd.model.Plano;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlanoRepository extends JpaRepository<Plano, Integer> {

	@Query("INSERT INTO Plano (fpkIdPlano, preco, duracaoEmMeses) VALUES (:fpkIdPlano, :preco, :duracaoMeses)")
    @Modifying
    @Transactional
    void salvarPlano(@Param("fpkIdPlano") Integer fpkIdPlano, @Param("preco") double preco, @Param("duracaoMeses") Integer duracaoMeses);
}
