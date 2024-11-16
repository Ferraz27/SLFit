package com.gymbd.repository;

import com.gymbd.model.Aluno;
import com.gymbd.model.Maquina;
import com.gymbd.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Integer> {

    // Método para buscar a máquina por ID usando SQL nativo
    @Query(value = "SELECT * FROM maquina WHERE pk_id_maquina = ?1", nativeQuery = true)
    Maquina buscarMaquinaPorId(Integer id);
	
	@Query(value = "SELECT * FROM maquina", nativeQuery = true)
	List<Maquina> listarTodasMaquinas();
	
	@Query("SELECT m FROM Maquina m LEFT JOIN FETCH m.exercicios")  // Utilizando LEFT JOIN para trazer as máquinas e seus exercícios
    List<Maquina> buscarMaquinasEExercicios();
	
	
	
}
