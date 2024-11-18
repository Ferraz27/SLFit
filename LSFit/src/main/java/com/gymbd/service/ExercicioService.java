package com.gymbd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymbd.model.Exercicio;
import com.gymbd.repository.ExercicioRepository;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    // Método para salvar o exercício
    public void salvarExercicio(Exercicio exercicio) {
        exercicioRepository.save(exercicio);
    }
    
    public void atualizarExercicio(Exercicio exercicio) {
        exercicioRepository.save(exercicio);
    }
    
    public List<Exercicio> listarExerciciosComDetalhes() {
        return exercicioRepository.buscarExerciciosComDetalhes();  // Chama o repositório para fazer a consulta
    }
    
    public void deletarExercicio(String nomeExercicio) {
        exercicioRepository.deletarExercicioPorId(nomeExercicio);
    }

    // Novo método para buscar exercício por nome
    public Exercicio buscarExercicioPorNome(String nome) {
        return exercicioRepository.buscarPorNomeExercicio(nome);
    }
}
