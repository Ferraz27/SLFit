package com.gymbd.service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Unidade;
import com.gymbd.repository.UnidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    
    
    public Unidade salvarUnidade(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }
    
    public List<Unidade> listarUnidades() {
        return unidadeRepository.listarTodosUnidades();
        
    }
    
    public Unidade buscarUnidadePorId(Integer id) {
        return unidadeRepository.buscarPorId(id);
    }
    
    public void deletarUnidade(Integer id) {
        // Primeiro, exclui o instrutor (que é uma entidade de pessoa)
        unidadeRepository.deleteById(id);
    }
    
    
}

