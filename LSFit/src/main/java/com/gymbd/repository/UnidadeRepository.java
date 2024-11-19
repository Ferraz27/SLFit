package com.gymbd.repository;


import com.gymbd.model.Aluno;
import com.gymbd.model.Unidade;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
    
	@Query(value = "SELECT * FROM unidade", nativeQuery = true)
    List<Unidade> listarTodosUnidades();
	
	@Query(value = "SELECT * FROM unidade WHERE pk_id_unidade = :id", nativeQuery = true)
    Unidade buscarPorId(@Param("id") Integer id);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Unidade u WHERE u.pkIdUnidade = :id")
    void deleteById(@Param("id") Integer id);
	
	
    @Query(value = "SELECT u.*, COUNT(a.fpk_id_aluno) as total_alunos " +
    "FROM unidade u " +
    "LEFT JOIN plano p ON u.pk_id_unidade = p.fk_id_unidade " +
    "LEFT JOIN aluno a ON p.fpk_id_plano = a.fpk_id_aluno " +
    "GROUP BY u.pk_id_unidade " +
    "ORDER BY total_alunos DESC " +
    "LIMIT 1", nativeQuery = true)
    Object findUnidadeWithMostAlunos();
	    

}
