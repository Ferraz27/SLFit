package com.gymbd.repository;

import com.gymbd.model.Instrutor;
import com.gymbd.model.Pessoa;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstrutorRepository extends JpaRepository<Instrutor, Integer> {
    
	@Query(value = "SELECT *" +
            "FROM pessoa p " +
			"JOIN instrutor i on p.pk_id_pessoa = i.fpk_id_instrutor " +
            "JOIN enderecos e on e.fpk_id_pessoa = p.pk_id_pessoa", nativeQuery = true)
List<Instrutor> listarInstrutores();
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Instrutor i WHERE i.pkIdPessoa = :id")
    void deleteById(@Param("id") Integer id);

}
