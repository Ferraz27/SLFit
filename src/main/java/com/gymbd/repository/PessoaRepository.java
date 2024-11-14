package com.gymbd.repository;

import com.gymbd.model.Pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    // Consulta JPQL para retornar Pessoa com seu Endereco
	@Query(value = "SELECT * FROM pessoa p " +
			"LEFT JOIN instrutor i on p.pk_id_pessoa = i.fpk_id_instrutor " +
            "LEFT JOIN enderecos e ON p.pk_id_pessoa = e.fpk_id_pessoa", nativeQuery = true)
	List<Pessoa> listarTodasPessoas();
}

