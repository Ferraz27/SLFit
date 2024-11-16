package com.gymbd.service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Pessoa;
import com.gymbd.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarAlunos() {
        return alunoRepository.listarTodosAlunos();
        
    }
    
    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    
    public void deletarAluno(Integer id) {
        // Primeiro, exclui o aluno (que é uma entidade de pessoa)
        alunoRepository.deleteById(id);
    }
}

