package com.gymbd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_pessoa")  // Mapeando explicitamente o nome da coluna
    private Integer pkIdPessoa;

    @Column(name = "cpf_pessoa", length = 11, nullable = false)
    private String cpfPessoa;

    @Column(name = "nome_pessoa", length = 100, nullable = false)
    private String nomePessoa;

    @Column(name = "telefone1_pessoa", length = 15)
    private String telefone1Pessoa;

    @Column(name = "telefone2_pessoa", length = 15)
    private String telefone2Pessoa;

    @OneToOne(mappedBy = "pessoa",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;
    

    // Getters e setters
    public Integer getPkIdPessoa() {
        return pkIdPessoa;
    }

    public void setPkIdPessoa(Integer pkIdPessoa) {
        this.pkIdPessoa = pkIdPessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getTelefone1Pessoa() {
        return telefone1Pessoa;
    }

    public void setTelefone1Pessoa(String telefone1Pessoa) {
        this.telefone1Pessoa = telefone1Pessoa;
    }

    public String getTelefone2Pessoa() {
        return telefone2Pessoa;
    }

    public void setTelefone2Pessoa(String telefone2Pessoa) {
        this.telefone2Pessoa = telefone2Pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        endereco.setPessoa(this); // Configura a referência bidirecional
    }
    
}
