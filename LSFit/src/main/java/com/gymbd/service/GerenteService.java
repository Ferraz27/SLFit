package com.gymbd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymbd.model.Gerente;
import com.gymbd.model.Instrutor;
import com.gymbd.repository.GerenteRepository;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente salvarGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }
    
    public void deletarGerente(Integer id) {
        // Primeiro, exclui o aluno (que é uma entidade de pessoa)
        gerenteRepository.deleteById(id);
    }
    
    public  List<Gerente> listarGerentes() {
        return gerenteRepository.listarGerentes(); // Busca todos os Instrutors do banco
    }
}
