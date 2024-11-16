package com.gymbd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Endereco;
import com.gymbd.model.Plano;
import com.gymbd.repository.AlunoRepository;
import com.gymbd.repository.PlanoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    

    // Buscar plano por ID
    public Plano buscarPlanoPorId(Integer id) {
    	return planoRepository.findById(id).orElse(null);
    }

    // Salvar plano
    public Plano salvarPlano(Plano plano) {
        return planoRepository.save(plano);
    }

    
    
    
}
