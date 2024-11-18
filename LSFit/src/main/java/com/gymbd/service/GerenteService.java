package com.gymbd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymbd.model.Gerente;
import com.gymbd.repository.GerenteRepository;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente salvarGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }
    
    public Gerente atualizarGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }
    
    public void deletarGerente(Integer id) {
        gerenteRepository.deleteById(id);
    }
    
    public List<Gerente> listarGerentes() {
        return gerenteRepository.listarGerentes();
    }

    public Gerente buscarPorId(Integer id) {
        return gerenteRepository.buscarPorId(id);
    }
}
