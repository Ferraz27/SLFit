package com.gymbd.service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Instrutor;
import com.gymbd.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstrutorService {

    @Autowired
    private  InstrutorRepository instrutorRepository;

    public  List<Instrutor> listarInstrutores() {
        return instrutorRepository.listarInstrutores(); // Busca todos os Instrutors do banco
    }
    
    public Instrutor salvarInstrutor(Instrutor instrutor) {
        return instrutorRepository.save(instrutor);
    }
}
