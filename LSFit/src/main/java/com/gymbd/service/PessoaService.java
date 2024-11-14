package com.gymbd.service;

import com.gymbd.model.Pessoa;
import com.gymbd.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private  PessoaRepository pessoaRepository;

    public  List<Pessoa> listarPessoas() {
    	System.out.println(pessoaRepository.listarTodasPessoas());
        return pessoaRepository.listarTodasPessoas(); // Busca todos os Instrutors do banco
    }
}
