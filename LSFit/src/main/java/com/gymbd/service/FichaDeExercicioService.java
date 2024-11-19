package com.gymbd.service;

import com.gymbd.model.FichaDeExercicio;
import com.gymbd.model.Aluno;
import com.gymbd.model.Instrutor;
import com.gymbd.model.Exercicio;
import com.gymbd.repository.FichaDeExercicioRepository;
import com.gymbd.repository.AlunoRepository;
import com.gymbd.repository.ExercicioRepository;
import com.gymbd.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FichaDeExercicioService {

    @Autowired
    private FichaDeExercicioRepository fichaDeExercicioRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private InstrutorRepository instrutorRepository;
    
    @Autowired
    private ExercicioRepository exercicioRepository;
    
    // Criação de uma nova ficha de exercício, associando aluno, instrutor e exercícios
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Salvar ficha de exercício
    @Transactional
    public void salvarFichaDeExercicio(FichaDeExercicio fichaDeExercicio) {
        String sql = "INSERT INTO ficha_de_treino (fk_id_instrutor, fk_id_aluno) VALUES (?, ?)";
        jdbcTemplate.update(sql, fichaDeExercicio.getInstrutor().getPkIdPessoa(), fichaDeExercicio.getAluno().getPkIdPessoa());

        // Obter o ID gerado para a ficha de treino
        Integer fichaId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Salvar os exercícios associados à ficha de treino
        for (Exercicio exercicio : fichaDeExercicio.getExercicios()) {
            String sqlExercicio = "INSERT INTO ficha_exercicio (fk_id_ficha_de_treino, fk_nome_exercicio) VALUES (?, ?)";
            jdbcTemplate.update(sqlExercicio, fichaId, exercicio.getNomeExercicio());
        }
    }

    // Recuperar os exercícios de uma ficha
    public List<Exercicio> getExerciciosByFichaId(Integer fichaId) {
        return fichaDeExercicioRepository.findExerciciosByFichaId(fichaId);
    }

    // Recuperar uma ficha de exercício por ID
    public FichaDeExercicio getFichaDeExercicioById(Integer fichaId) {
        return fichaDeExercicioRepository.findFichaDeExercicioById(fichaId);
    }

    // Buscar uma ficha de exercício por aluno e instrutor
    public List<FichaDeExercicio> getFichasByAlunoAndInstrutor(Integer alunoId, Integer instrutorId) {
        return fichaDeExercicioRepository.findByAlunoAndInstrutor(alunoId, instrutorId);
    }
    
    public List<FichaDeExercicio> listarFichasDeExercicio() {
        return fichaDeExercicioRepository.findAllFichaDeExercicio();
    }

    // Método para buscar uma ficha de exercício detalhada
    public FichaDeExercicio buscarFichaDeExercicioDetalhada(Integer id) {
        return fichaDeExercicioRepository.findFichaDeExercicioDetalhada(id);
    }
    
    public void deletarFicha(Integer id) {
        fichaDeExercicioRepository.deletarFichaPorId(id);
    }

    @Transactional
    public void atualizarFichaDeExercicio(FichaDeExercicio fichaDeExercicio) {
        String sql = "UPDATE ficha_de_treino SET fk_id_instrutor = ?, fk_id_aluno = ? WHERE pk_id_ficha_de_treino = ?";
        jdbcTemplate.update(sql, fichaDeExercicio.getInstrutor().getPkIdPessoa(), fichaDeExercicio.getAluno().getPkIdPessoa(), fichaDeExercicio.getPkIdFichaDeTreino());

        // Remover os exercícios antigos
        String sqlDeleteExercicios = "DELETE FROM ficha_exercicio WHERE fk_id_ficha_de_treino = ?";
        jdbcTemplate.update(sqlDeleteExercicios, fichaDeExercicio.getPkIdFichaDeTreino());

        // Adicionar os novos exercícios
        for (Exercicio exercicio : fichaDeExercicio.getExercicios()) {
            String sqlExercicio = "INSERT INTO ficha_exercicio (fk_id_ficha_de_treino, fk_nome_exercicio) VALUES (?, ?)";
            jdbcTemplate.update(sqlExercicio, fichaDeExercicio.getPkIdFichaDeTreino(), exercicio.getNomeExercicio());
        }
    }
}
