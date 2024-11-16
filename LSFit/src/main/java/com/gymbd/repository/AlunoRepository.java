package com.gymbd.repository;

import com.gymbd.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	@Query(value = "SELECT *" +
            "FROM pessoa p " +
			"JOIN aluno a on p.pk_id_pessoa = a.fpk_id_aluno " +
            "JOIN enderecos e on e.fpk_id_pessoa = p.pk_id_pessoa", nativeQuery = true)
    List<Aluno> listarTodosAlunos();
}
