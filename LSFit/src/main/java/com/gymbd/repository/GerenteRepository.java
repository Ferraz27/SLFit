package com.gymbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gymbd.model.Gerente;
import com.gymbd.model.Instrutor;

import jakarta.transaction.Transactional;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
	@Modifying
    @Transactional
    @Query("DELETE FROM Gerente g WHERE g.pkIdPessoa = :id")
    void deleteById(@Param("id") Integer id);
	
	@Query(value = "SELECT *" +
            "FROM pessoa p " +
			"JOIN gerente g on p.pk_id_pessoa = g.fpk_id_gerente " +
            "JOIN enderecos e on e.fpk_id_pessoa = p.pk_id_pessoa " + 
			"JOIN unidade u on u.fk_id_gerente = g.fpk_id_gerente ", nativeQuery = true)
	List<Gerente> listarGerentes();
}
