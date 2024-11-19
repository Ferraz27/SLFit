package com.gymbd.service;

import com.gymbd.model.Gerente;
import com.gymbd.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Salvar gerente
    @Transactional
    public void salvarGerente(Gerente gerente) {
        salvarOnlyPessoa(gerente); // Salva os dados da entidade Pessoa e define o ID
        salvarOnlyGerente(gerente); // Salva os dados específicos da entidade Gerente
    }

    // Atualizar gerente
    @Transactional
    public void atualizarGerente(Gerente gerente) {
        atualizarOnlyPessoa(gerente); // Atualiza os dados da entidade Pessoa
        atualizarOnlyGerente(gerente); // Atualiza os dados específicos da entidade Gerente
    }

    public void salvarOnlyGerente(Gerente gerente) {
        String sql = "INSERT INTO gerente (fpk_id_gerente, fk_id_unidade) VALUES (?, ?)";
        jdbcTemplate.update(sql, gerente.getPkIdPessoa(), gerente.getUnidade() != null ? gerente.getUnidade().getPkIdUnidade() : null);
    }

    public void atualizarOnlyGerente(Gerente gerente) {
        String sql = "UPDATE gerente SET fk_id_unidade = ? WHERE fpk_id_gerente = ?";
        jdbcTemplate.update(sql, gerente.getUnidade() != null ? gerente.getUnidade().getPkIdUnidade() : null, gerente.getPkIdPessoa());
    }

    public void salvarOnlyPessoa(Gerente gerente) {
        String sql = "INSERT INTO pessoa (cpf_pessoa, nome_pessoa, telefone1_pessoa, telefone2_pessoa) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, gerente.getCpfPessoa(), gerente.getNomePessoa(), gerente.getTelefone1Pessoa(), gerente.getTelefone2Pessoa());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        gerente.setPkIdPessoa(id);
    }

    public void atualizarOnlyPessoa(Gerente gerente) {
        String sql = "UPDATE pessoa SET cpf_pessoa = ?, nome_pessoa = ?, telefone1_pessoa = ?, telefone2_pessoa = ? WHERE pk_id_pessoa = ?";
        jdbcTemplate.update(sql, gerente.getCpfPessoa(), gerente.getNomePessoa(), gerente.getTelefone1Pessoa(), gerente.getTelefone2Pessoa(), gerente.getPkIdPessoa());
    }

    @Transactional
    public void deletarGerente(Integer id) {
        gerenteRepository.deleteById(id);
    }

    public List<Gerente> listarGerentes() {
        return gerenteRepository.listarGerentes();
    }

    public Gerente buscarPorId(Integer id) {
        return gerenteRepository.buscarPorId(id);
    }
}