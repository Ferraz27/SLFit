package com.gymbd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gymbd.model.Aluno;
import com.gymbd.model.Endereco;
import com.gymbd.service.AlunoService;
import com.gymbd.service.EnderecoService;
import com.gymbd.model.Instrutor;
import com.gymbd.model.Maquina;
import com.gymbd.model.Pessoa;
import com.gymbd.model.Unidade;
import com.gymbd.service.InstrutorService;
import com.gymbd.service.PessoaService;
import com.gymbd.service.UnidadeService;
import com.gymbd.service.MaquinaService;



@Controller
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private InstrutorService instrutorService;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private UnidadeService unidadeService;
	@Autowired
	private MaquinaService maquinaService;
	
	
    @GetMapping("/home")
    public String home() {
        return "index";  // O Spring Boot buscará 'index.html' automaticamente
    }
    
    @GetMapping("/pagina-pessoas")
    public String paginaPessoas(Model model) {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        model.addAttribute("pessoas", pessoas);  // Passa a lista de alunos para a view
        return "pagina-pessoas";
    }
    
    @GetMapping("/pagina-instrutores")
    public String paginaInstrutor(Model model) {
        List<Instrutor> instrutores = instrutorService.listarInstrutores();
        model.addAttribute("instrutores", instrutores); 
        return "pagina-instrutores";
    }
    
    @PostMapping("pessoas/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa) {
        pessoaService.salvarPessoa(pessoa);
        return "redirect:/pessoas/form";
    }
    
    @GetMapping("pessoas/form")
    public String mostrarFormularioPessoa() {
        return "pessoa-form";
    }
    
    @PostMapping("alunos/salvar")
    public String salvarAluno(@ModelAttribute Aluno aluno, @ModelAttribute Endereco endereco) {
    	alunoService.salvarAluno(aluno);
    	endereco.setFpkIdPessoa(aluno.getPkIdPessoa());
        enderecoService.salvarEndereco(endereco);
        
        
        return "redirect:/pagina-pessoas";
    }

    
    @GetMapping("alunos/form")
    public String mostrarFormularioAluno() {
        return "aluno-form";
    }
    
    @PostMapping("instrutor/salvar")
    public String salvarInstrutor(@ModelAttribute Instrutor instrutor, @ModelAttribute Endereco endereco) {
    	instrutorService.salvarInstrutor(instrutor);
    	endereco.setFpkIdPessoa(instrutor.getPkIdPessoa());
        enderecoService.salvarEndereco(endereco);
        
        
        return "redirect:/pagina-instrutores";
    }

    
    @GetMapping("instrutor/form")
    public String mostrarFormularioInstrutor() {
        return "instrutor-form";
    }
    
    @PostMapping("unidade/salvar")
    public String salvarUnidade(@ModelAttribute Unidade unidade) {
    	System.out.println(unidade.getRua());
        unidadeService.salvarUnidade(unidade);
        
        
        return "redirect:/pagina-instrutores";
    }

    
    @GetMapping("unidade/form")
    public String mostrarFormularioUnidade() {
        return "unidade-form";
    }
    
    @GetMapping("/pagina-unidades")
    public String paginaUnidade(Model model) {
        List<Unidade> unidades = unidadeService.listarUnidades();
        model.addAttribute("unidades", unidades); 
        return "pagina-unidades";
    }
    
    @PostMapping("maquina/salvar")
    public String salvarMaquina(@ModelAttribute Maquina maquina) {
        maquinaService.salvarMaquina(maquina);
        
        
        return "redirect:/home";
    }

    
    @GetMapping("maquina/form")
    public String mostrarFormularioMaquina() {
        return "maquina-form";
    }
    
    @GetMapping("/pagina-maquinas")
    public String paginaMaquina(Model model) {
        List<Maquina> maquinas = maquinaService.listarMaquinas();
        model.addAttribute("maquinas", maquinas); 
        return "pagina-maquinas";
    }
}
