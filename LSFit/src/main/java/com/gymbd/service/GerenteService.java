package com.gymbd.service;

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
}
