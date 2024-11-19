package com.gymbd.service;

import com.gymbd.model.Instrutor;
import com.gymbd.repository.InstrutorRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstrutorService {

    @Autowired
    private InstrutorRepository instrutorRepository;

    public List<Instrutor> listarInstrutores() {
        return instrutorRepository.listarInstrutores(); // Busca todos os Instrutors do banco
    }
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void salvarOnlyInstrutor(Instrutor instrutor) {
        String sql = "INSERT INTO instrutor (fpk_id_instrutor, salario_instrutor) VALUES (?, ?)";
        jdbcTemplate.update(sql, instrutor.getPkIdPessoa(), instrutor.getSalarioInstrutor());
    }

    // Atualizar instrutor
    public void atualizarOnlyInstrutor(Instrutor instrutor) {
        String sql = "UPDATE instrutor SET salario_instrutor = ? WHERE fpk_id_instrutor = ?";
        jdbcTemplate.update(sql, instrutor.getSalarioInstrutor(), instrutor.getPkIdPessoa());
    }

    public void salvarOnlyPessoa(Instrutor instrutor) {
        String sql = "INSERT INTO pessoa (cpf_pessoa, nome_pessoa, telefone1_pessoa, telefone2_pessoa) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, instrutor.getCpfPessoa(), instrutor.getNomePessoa(), instrutor.getTelefone1Pessoa(), instrutor.getTelefone2Pessoa());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        instrutor.setPkIdPessoa(id);
    }

    public void atualizarOnlyPessoa(Instrutor instrutor) {
        String sql = "UPDATE pessoa SET cpf_pessoa = ?, nome_pessoa = ?, telefone1_pessoa = ?, telefone2_pessoa = ? WHERE pk_id_pessoa = ?";
        jdbcTemplate.update(sql, instrutor.getCpfPessoa(), instrutor.getNomePessoa(), instrutor.getTelefone1Pessoa(), instrutor.getTelefone2Pessoa(), instrutor.getPkIdPessoa());
    }

    @Transactional
    public void salvarInstrutor(Instrutor instrutor) {
        salvarOnlyPessoa(instrutor); // Salva os dados da entidade Pessoa e define o ID
        salvarOnlyInstrutor(instrutor); // Salva os dados específicos da entidade Instrutor
    }

    @Transactional
    public void atualizarInstrutor(Instrutor instrutor) {
        atualizarOnlyPessoa(instrutor); // Atualiza os dados da entidade Pessoa
        atualizarOnlyInstrutor(instrutor); // Atualiza os dados específicos da entidade Instrutor
    }
    
    public void deletarInstrutor(Integer id) {
        // Primeiro, exclui o instrutor (que é uma entidade de pessoa)
        instrutorRepository.deleteById(id);
    }

    public Instrutor buscarInstrutorPorId(Integer id) {
        return instrutorRepository.findByIdd(id);
    }

    public List<Instrutor> findInstrutoresWithSalaryAboveAverage() {
        return instrutorRepository.findInstrutoresWithSalaryAboveAverage();
    }
}
