package com.gymbd.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="ficha_de_treino")
public class FichaDeExercicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pk_id_ficha_de_treino")
    private Integer pkIdFichaDeTreino;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "ficha_exercicio",
        joinColumns = @JoinColumn(name = "fk_id_ficha_de_treino"),
        inverseJoinColumns = @JoinColumn(name = "fk_nome_exercicio")
    )
    private List<Exercicio> exercicios;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_instrutor")
    private Instrutor instrutor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_aluno")
    private Aluno aluno;

    // Getters and Setters

    public Integer getPkIdFichaDeTreino() {
        return pkIdFichaDeTreino;
    }

    public void setPkIdFichaDeTreino(Integer pkIdFichaDeTreino) {
        this.pkIdFichaDeTreino = pkIdFichaDeTreino;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
