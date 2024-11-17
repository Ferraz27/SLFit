package com.gymbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gymbd.model.Exercicio;

import jakarta.transaction.Transactional;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, String> {
	@Query("SELECT e FROM Exercicio e " +
	           "JOIN FETCH e.maquina m")  // Recupera os exercícios e junta com as máquinas associadas
	    List<Exercicio> buscarExerciciosComDetalhes();
	
	@Query("SELECT e FROM Exercicio e WHERE e.nomeExercicio IN :nomeExercicios")
    List<Exercicio> findByNomeExercicioIn(List<String> nomeExercicios);
	
	@Modifying
	@Transactional
    @Query(value = "DELETE FROM exercicio WHERE pk_nome_exercicio = :nome", nativeQuery = true)
    void deletarExercicioPorId(@Param("nome") String nome);
}

