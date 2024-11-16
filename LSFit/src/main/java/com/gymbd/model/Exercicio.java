package com.gymbd.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="exercicio")
public class Exercicio {
    
    @Id
    @Column(name="pk_nome_exercicio", length = 50, nullable = false)
    private String nomeExercicio;
    
    @Column(name="series")
    private int series;
    
    @Column(name="repeticoes")
    private int numeroDeRepeticoes;
    
    @Column(name="tempo_de_descanso")
    private int tempoDescanso;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_maquina", nullable = false) // Chave estrangeira na tabela 'exercicio'
    private Maquina maquina;

    @ManyToMany(mappedBy = "exercicios", fetch = FetchType.LAZY)
    private List<FichaDeExercicio> fichasDeExercicio;

    // Getters and Setters

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getNumeroDeRepeticoes() {
        return numeroDeRepeticoes;
    }

    public void setNumeroDeRepeticoes(int numeroDeRepeticoes) {
        this.numeroDeRepeticoes = numeroDeRepeticoes;
    }

    public int getTempoDescanso() {
        return tempoDescanso;
    }

    public void setTempoDescanso(int tempoDescanso) {
        this.tempoDescanso = tempoDescanso;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public List<FichaDeExercicio> getFichasDeExercicio() {
        return fichasDeExercicio;
    }

    public void setFichasDeExercicio(List<FichaDeExercicio> fichasDeExercicio) {
        this.fichasDeExercicio = fichasDeExercicio;
    }
}
