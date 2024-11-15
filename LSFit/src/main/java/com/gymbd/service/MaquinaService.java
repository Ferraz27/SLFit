package com.gymbd.service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Maquina;
import com.gymbd.model.Pessoa;
import com.gymbd.repository.AlunoRepository;
import com.gymbd.repository.MaquinaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    
    public Maquina salvarMaquina(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }
    
    public  List<Maquina> listarMaquinas() {
        return maquinaRepository.listarTodasMaquinas(); // Busca todos os Instrutors do banco
    }
}
