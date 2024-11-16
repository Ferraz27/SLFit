package com.gymbd.repository;

import com.gymbd.model.Aluno;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	@Query(value = "SELECT *" +
            "FROM pessoa p " +
			"JOIN aluno a on p.pk_id_pessoa = a.fpk_id_aluno " +
            "JOIN enderecos e on e.fpk_id_pessoa = p.pk_id_pessoa " +
			"JOIN plano p2 on p2.fpk_id_plano = p.pk_id_pessoa ", nativeQuery = true)
    List<Aluno> listarTodosAlunos();
	
	@Modifying
	@Query("UPDATE Aluno a SET a.nomePessoa = :nome, a.cpfPessoa = :cpf, a.telefone1Pessoa = :telefone1, a.telefone2Pessoa = :telefone2 WHERE a.pkIdPessoa = :id")
	void atualizarAluno(@Param("id") Integer id, @Param("nome") String nome, @Param("cpf") String cpf,
	                    @Param("telefone1") String telefone1, @Param("telefone2") String telefone2);
	
	// Deletar Aluno por ID (irá excluir também da tabela 'pessoa' devido à herança JOINED)
    @Modifying
    @Transactional
    @Query("DELETE FROM Aluno a WHERE a.pkIdPessoa = :id")
    void deleteById(@Param("id") Integer id);
}
