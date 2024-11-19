package com.gymbd.service;

import com.gymbd.model.Aluno;
import com.gymbd.model.Unidade;
import com.gymbd.repository.UnidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Salvar unidade
    public void salvarUnidade(Unidade unidade) {
        String sql = "INSERT INTO unidade (rua, bairro, numero, cep, telefone1, telefone2, fk_id_gerente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, unidade.getRua(), unidade.getBairro(), unidade.getNumero(), unidade.getCep(), unidade.getTelefone1Unidade(), unidade.getTelefone2Unidade(), unidade.getGerente() != null ? unidade.getGerente().getPkIdPessoa() : null);
    }

    // Atualizar unidade
    public void atualizarUnidade(Unidade unidade) {
        String sql = "UPDATE unidade SET rua = ?, bairro = ?, numero = ?, cep = ?, telefone1 = ?, telefone2 = ?, fk_id_gerente = ? WHERE pk_id_unidade = ?";
        jdbcTemplate.update(sql, unidade.getRua(), unidade.getBairro(), unidade.getNumero(), unidade.getCep(), unidade.getTelefone1Unidade(), unidade.getTelefone2Unidade(), unidade.getGerente() != null ? unidade.getGerente().getPkIdPessoa() : null, unidade.getPkIdUnidade());
    }
    
    
    public List<Unidade> listarUnidades() {
        return unidadeRepository.listarTodosUnidades();
        
    }
    
    public Unidade buscarUnidadePorId(Integer id) {
        return unidadeRepository.buscarPorId(id);
    }
    
    public void deletarUnidade(Integer id) {
        // Primeiro, exclui o instrutor (que é uma entidade de pessoa)
        unidadeRepository.deleteById(id);
    }
    
    public Object findUnidadeWithMostAlunos(){
        return unidadeRepository.findUnidadeWithMostAlunos();
    };

    
}

