package com.gymbd.repository;


import com.gymbd.model.Aluno;
import com.gymbd.model.Unidade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
    
	@Query(value = "SELECT * FROM unidade", nativeQuery = true)
    List<Unidade> listarTodosUnidades();
	
	@Query(value = "SELECT * FROM unidade WHERE pk_id_unidade = :id", nativeQuery = true)
    Unidade buscarPorId(@Param("id") Integer id);
}
