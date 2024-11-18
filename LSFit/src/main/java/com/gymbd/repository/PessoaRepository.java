package com.gymbd.repository;

import com.gymbd.model.Pessoa;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    // Consulta JPQL para retornar Pessoa com seu Endereco
	@Query(value = "SELECT * FROM pessoa p " +
			"LEFT JOIN instrutor i on p.pk_id_pessoa = i.fpk_id_instrutor " +
			"LEFT JOIN aluno a on p.pk_id_pessoa = a.fpk_id_aluno " +
			"LEFT JOIN gerente g on p.pk_id_pessoa = g.fpk_id_gerente " +
            "LEFT JOIN enderecos e ON p.pk_id_pessoa = e.fpk_id_pessoa", nativeQuery = true)
	List<Pessoa> listarTodasPessoas();
	
	@Query("SELECT MAX(p.pkIdPessoa) FROM Pessoa p")
    Integer findMaxId();
	
	

}

