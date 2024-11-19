package com.gymbd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gymbd.model.Exercicio;
import com.gymbd.repository.ExercicioRepository;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    // Método para salvar o exercício
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Salvar exercicio
    public void salvarExercicio(Exercicio exercicio) {
        String sql = "INSERT INTO exercicio (pk_nome_exercicio, series, repeticoes, tempo_de_descanso, fk_id_maquina) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, exercicio.getNomeExercicio(), exercicio.getSeries(), exercicio.getNumeroDeRepeticoes(), exercicio.getTempoDescanso(), exercicio.getMaquina().getPkIdMaquina());
    }

    // Atualizar exercicio
    public void atualizarExercicio(Exercicio exercicio) {
        String sql = "UPDATE exercicio SET series = ?, repeticoes = ?, tempo_de_descanso = ?, fk_id_maquina = ? WHERE pk_nome_exercicio = ?";
        jdbcTemplate.update(sql, exercicio.getSeries(), exercicio.getNumeroDeRepeticoes(), exercicio.getTempoDescanso(), exercicio.getMaquina().getPkIdMaquina(), exercicio.getNomeExercicio());
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
