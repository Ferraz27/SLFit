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
    @Transactional
    public FichaDeExercicio criarFichaDeExercicio(Integer alunoId, Integer instrutorId, List<String> exercicios) {
        // Buscar o aluno e instrutor a partir dos seus IDs
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Instrutor instrutor = instrutorRepository.findById(instrutorId).orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        // Criar a FichaDeExercicio
        FichaDeExercicio fichaDeExercicio = new FichaDeExercicio();
        fichaDeExercicio.setAluno(aluno);
        fichaDeExercicio.setInstrutor(instrutor);

        // Buscar os Exercícios com base na lista de nomes
        List<Exercicio> listaExercicios = exercicioRepository.findByNomeExercicioIn(exercicios);

        // Associar os Exercícios à FichaDeExercicio
        fichaDeExercicio.setExercicios(listaExercicios);

        // Salvar a FichaDeExercicio (isso também salva a relação com os exercícios)
        fichaDeExercicio = fichaDeExercicioRepository.save(fichaDeExercicio);

        return fichaDeExercicio;
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
    
    
}
