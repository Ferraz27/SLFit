package com.gymbd.repository;

import com.gymbd.model.FichaDeExercicio;
import com.gymbd.model.Exercicio;
import com.gymbd.model.Instrutor;
import com.gymbd.model.Aluno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaDeExercicioRepository extends JpaRepository<FichaDeExercicio, Integer> {

    // Consulta SQL para buscar uma ficha de exercício por aluno e instrutor
    @Query(value = "SELECT * FROM ficha_de_treino f " +
                   "INNER JOIN ficha_exercicio fe ON f.pk_id_ficha_de_treino = fe.fk_id_ficha_de_treino " +
                   "WHERE f.fk_id_aluno = :alunoId AND f.fk_id_instrutor = :instrutorId", nativeQuery = true)
    List<FichaDeExercicio> findByAlunoAndInstrutor(@Param("alunoId") Integer alunoId, 
                                                   @Param("instrutorId") Integer instrutorId);
    
    // Consulta SQL para buscar os exercícios de uma ficha de exercício
    @Query(value = "SELECT e.* FROM exercicio e " +
                   "INNER JOIN ficha_exercicio fe ON e.pk_nome_exercicio = fe.fk_nome_exercicio " +
                   "WHERE fe.fk_id_ficha_de_treino = :fichaId", nativeQuery = true)
    List<Exercicio> findExerciciosByFichaId(@Param("fichaId") Integer fichaId);
    
    // Consulta SQL para buscar a ficha de exercício por ID
    @Query(value = "SELECT * FROM ficha_de_treino WHERE pk_id_ficha_de_treino = :fichaId", nativeQuery = true)
    FichaDeExercicio findFichaDeExercicioById(@Param("fichaId") Integer fichaId);
}
