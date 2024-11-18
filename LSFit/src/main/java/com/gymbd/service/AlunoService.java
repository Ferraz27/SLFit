package com.gymbd.service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Pessoa;
import com.gymbd.repository.AlunoRepository;
import com.gymbd.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private  PessoaRepository pessoaRepository;


    public List<Aluno> listarAlunos() {
        return alunoRepository.listarTodosAlunos();
        
    }
    
    public void salvarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
      
    }
    
    public void atualizarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
      
    }
    
    public void deletarAluno(Integer id) {
        // Primeiro, exclui o aluno (que é uma entidade de pessoa)
        alunoRepository.deleteById(id);
    }

    public Aluno buscarAlunoPorId(Integer id) {
        return alunoRepository.findByIdd(id);
    }


}

