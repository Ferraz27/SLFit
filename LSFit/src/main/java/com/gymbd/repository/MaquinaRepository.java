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
	
	@Query(value = "SELECT * FROM maquina", nativeQuery = true)
	List<Maquina> listarTodasMaquinas();
	
	
	
}
