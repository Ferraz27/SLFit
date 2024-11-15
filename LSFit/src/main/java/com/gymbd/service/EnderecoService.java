package com.gymbd.service;

import com.gymbd.model.Endereco;
import com.gymbd.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Método para salvar um Endereco
    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    // Método para buscar um Endereco por id
    public Endereco buscarEnderecoPorId(Integer id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    // Adicione outros métodos conforme necessário
}
