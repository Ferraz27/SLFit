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
    


    public void salvarPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }
    
    public Integer	 getNextPessoaId() {
        Integer maxId = pessoaRepository.findMaxId();
        if (maxId == null) {
            return 1; // Se não houver registros, começa com 1
        }
        return maxId + 1; // Retorna o próximo ID
    }
}
