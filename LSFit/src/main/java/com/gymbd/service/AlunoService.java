package com.gymbd.service;

import com.gymbd.model.Aluno;
import com.gymbd.repository.AlunoRepository;
import com.gymbd.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Aluno> listarAlunos() {
        return alunoRepository.listarTodosAlunos();
    }

    public void salvarOnlyAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno (fpk_id_aluno, fk_id_que_indicou) VALUES (?, ?)";
        jdbcTemplate.update(sql, aluno.getPkIdPessoa(), aluno.getIndicacao() != null ? aluno.getIndicacao().getPkIdPessoa() : null);
    }

    public void atualizarOnlyAluno(Aluno aluno) {
        String sql = "UPDATE aluno SET fk_id_que_indicou = ? WHERE fpk_id_aluno = ?";
        jdbcTemplate.update(sql, aluno.getIndicacao() != null ? aluno.getIndicacao().getPkIdPessoa() : null, aluno.getPkIdPessoa());
    }

    public void salvarOnlyPessoa(Aluno aluno) {
        String sql = "INSERT INTO pessoa (cpf_pessoa, nome_pessoa, telefone1_pessoa, telefone2_pessoa) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, aluno.getCpfPessoa(), aluno.getNomePessoa(), aluno.getTelefone1Pessoa(), aluno.getTelefone2Pessoa());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        aluno.setPkIdPessoa(id);
    }

    public void atualizarOnlyPessoa(Aluno aluno) {
        String sql = "UPDATE pessoa SET cpf_pessoa = ?, nome_pessoa = ?, telefone1_pessoa = ?, telefone2_pessoa = ? WHERE pk_id_pessoa = ?";
        jdbcTemplate.update(sql, aluno.getCpfPessoa(), aluno.getNomePessoa(), aluno.getTelefone1Pessoa(), aluno.getTelefone2Pessoa(), aluno.getPkIdPessoa());
    }

    @Transactional
    public void salvarAluno(Aluno aluno) {
        salvarOnlyPessoa(aluno); // Salva os dados da entidade Pessoa e define o ID
        salvarOnlyAluno(aluno); // Salva os dados específicos da entidade Aluno
    }

    @Transactional
    public void atualizarAluno(Aluno aluno) {
        atualizarOnlyPessoa(aluno); // Atualiza os dados da entidade Pessoa
        atualizarOnlyAluno(aluno); // Atualiza os dados específicos da entidade Aluno
    }

    @Transactional
    public void deletarAluno(Integer id) {
        alunoRepository.deleteById(id);
    }

    public Aluno buscarAlunoPorId(Integer id) {
        return alunoRepository.findByIdd(id);
    }
}