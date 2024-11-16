package com.gymbd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymbd.model.Aluno;
import com.gymbd.model.Endereco;
import com.gymbd.model.Exercicio;
import com.gymbd.model.FichaDeExercicio;
import com.gymbd.model.Gerente;
import com.gymbd.service.AlunoService;
import com.gymbd.service.EnderecoService;
import com.gymbd.service.ExercicioService;
import com.gymbd.service.FichaDeExercicioService;
import com.gymbd.service.GerenteService;
import com.gymbd.model.Instrutor;
import com.gymbd.model.Maquina;
import com.gymbd.model.Pessoa;
import com.gymbd.model.Plano;
import com.gymbd.model.Unidade;
import com.gymbd.service.InstrutorService;
import com.gymbd.service.PessoaService;
import com.gymbd.service.PlanoService;
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
	@Autowired
	private GerenteService gerenteService;
	@Autowired
    private ExercicioService exercicioService;
	@Autowired
    private FichaDeExercicioService fichaDeExercicioService;
	@Autowired
    private PlanoService planoService;
	
	
    @GetMapping("/home")
    public String home() {
        return "index";  // O Spring Boot buscará 'index.html' automaticamente
    }
    
    @GetMapping("/pessoas")
    public String paginaPessoas(Model model) {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        model.addAttribute("pessoas", pessoas);  // Passa a lista de alunos para a view
        return "Read/ReadPessoa";
    }
    
    @PostMapping("pessoas/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa) {
        pessoaService.salvarPessoa(pessoa);
        return "redirect:/pessoas/form";
    }
    
    @GetMapping("pessoas/form")
    public String mostrarFormularioPessoa() {
        return "Create/CreatePessoa";
    }
    
    @GetMapping("/instrutores")
    public String paginaInstrutor(Model model) {
        List<Instrutor> instrutores = instrutorService.listarInstrutores();
        model.addAttribute("instrutores", instrutores); 
        return "Read/ReadInstrutor";
    }
    
    @PostMapping("instrutor/salvar")
    public String salvarInstrutor(@ModelAttribute Instrutor instrutor, @ModelAttribute Endereco endereco) {
    	instrutorService.salvarInstrutor(instrutor);
    	endereco.setFpkIdPessoa(instrutor.getPkIdPessoa());
    	
        enderecoService.salvarEndereco(endereco);
        
        
        
        return "redirect:/instrutores";
    }

    
    @GetMapping("instrutor/form")
    public String mostrarFormularioInstrutor() {
        return "Create/CreateInstrutor";
    }
    
    @GetMapping("/instrutor/deletar/{id}")
    public String deletarInstrutor(@PathVariable Integer id) {
        instrutorService.deletarInstrutor(id);
        return "redirect:/instrutores"; // Redireciona para a lista de instrutores
    }
    
    @GetMapping("/alunos")
    public String paginaAluno(Model model) {
        List<Aluno> alunos = alunoService.listarAlunos();
        model.addAttribute("alunos", alunos); 
        return "Read/ReadAluno";
    }
    
    
    @PostMapping("aluno/salvar")
    public String salvarAluno(@ModelAttribute Aluno aluno, @ModelAttribute Endereco endereco, @ModelAttribute Plano plano) {
    	alunoService.salvarAluno(aluno);
    	endereco.setFpkIdPessoa(aluno.getPkIdPessoa());
    	plano.setFpkIdPlano(aluno.getPkIdPessoa());
        enderecoService.salvarEndereco(endereco);
        planoService.salvarPlano(plano);
        
        
        return "redirect:/Read/ReadPessoas";
    }

    
    @GetMapping("aluno/form")
    public String mostrarFormularioAluno() {
        return "Create/CreateAluno";
    }
    
    @GetMapping("/aluno/deletar/{id}")
    public String deletarAluno(@PathVariable Integer id) {
        alunoService.deletarAluno(id);
        return "redirect:/alunos"; // Redireciona para a lista de alunos
    }
    
    
    
    
    
    @PostMapping("unidade/salvar")
    public String salvarUnidade(@ModelAttribute Unidade unidade) {
    	System.out.println(unidade.getRua());
        unidadeService.salvarUnidade(unidade);
        
        
        return "redirect:/unidades";
    }

    
    @GetMapping("unidade/form")
    public String mostrarFormularioUnidade() {
        return "Create/CreateUnidade";
    }
    
    @GetMapping("unidades")
    public String paginaUnidade(Model model) {
        List<Unidade> unidades = unidadeService.listarUnidades();
        model.addAttribute("unidades", unidades); 
        return "Read/ReadUnidade";
    }
    
    @PostMapping("maquina/salvar")
    public String salvarMaquina(@ModelAttribute Maquina maquina) {
        maquinaService.salvarMaquina(maquina);
        
        
        return "redirect:/home";
    }

    
    @GetMapping("maquina/form")
    public String mostrarFormularioMaquina() {
        return "Create/CreateMaquina";
    }
    
    @GetMapping("/maquinas")
    public String listarMaquinasEExercicios(Model model) {
        List<Maquina> maquinas = maquinaService.listarMaquinasEExercicios();
        model.addAttribute("maquinas", maquinas);  // Passa a lista de máquinas e exercícios para a view
        return "Read/ReadMaquinaExercicio";  // Nome da view (o HTML que você passou)
    }
    
    @PostMapping("gerente/salvar")
    public String salvarGerente(@ModelAttribute Gerente gerente, @ModelAttribute Endereco endereco, Integer unidadeId) {
        // Verifica se a unidade existe
        Unidade unidade = unidadeService.buscarUnidadePorId(unidadeId);
        if (unidade != null) {
            // Associa o gerente à unidade
            gerente.setUnidade(unidade);
            
        }
        
        // Salva o gerente
        gerenteService.salvarGerente(gerente);

        // Salva o endereço do gerente
        endereco.setFpkIdPessoa(gerente.getPkIdPessoa());
        enderecoService.salvarEndereco(endereco);
        
        return "redirect:/instrutores"; // Redireciona para a página de instrutores
    }
    
    @GetMapping("gerente/form")
    public String mostrarFormularioGerente() {
        return "Create/CreateGerente";
    }
    
    @GetMapping("/exercicios")
    public String listarExercicios(Model model) {
        List<Exercicio> exercicios = exercicioService.listarExerciciosComDetalhes();
        model.addAttribute("exercicios", exercicios);  // Passa a lista de exercícios para a view
        return "Read/ReadExercicio";  // Nome da view (o HTML para exibir os exercícios)
    }
    
    @GetMapping("exercicio/form")
    public String mostrarFormularioExercicio(Model model) {
        // Recupera a lista de máquinas para exibir no formulário
        List<Maquina> maquinas = maquinaService.listarMaquinas();
        model.addAttribute("maquinas", maquinas);  // Passa a lista de máquinas para a view
        model.addAttribute("exercicio", new Exercicio());  // Passa um objeto Exercicio vazio para o form
        return "Create/CreateExercicio";  // O caminho do template do formulário
    }
    
    @PostMapping("exercicio/salvar")
    public String salvarExercicio(@ModelAttribute Exercicio exercicio, @RequestParam Integer maquinaId) {
        // Recupera a máquina pelo ID
        Maquina maquina = maquinaService.buscarMaquinaPorId(maquinaId);
        if (maquina != null) {
            exercicio.setMaquina(maquina);  // Associa a máquina ao exercício
            exercicioService.salvarExercicio(exercicio);  // Salva o exercício
        }
        return "redirect:/exercicios";  // Redireciona para a página de exercícios
    }
 // Página de criação da ficha de exercício
    @GetMapping("/fichaExercicio/form")
    public String mostrarFormularioFichaDeExercicio(Model model) {
        model.addAttribute("alunos", alunoService.listarAlunos());
        model.addAttribute("instrutores", instrutorService.listarInstrutores());
        model.addAttribute("exercicios", exercicioService.listarExerciciosComDetalhes());
        model.addAttribute("fichaDeExercicio", new FichaDeExercicio());
        return "Create/CreateFichaDeExercicio";
    }

    // Salvando a ficha de exercício
    @PostMapping("/fichaExercicio/salvar")
    public String salvarFichaDeExercicio(@ModelAttribute FichaDeExercicio fichaDeExercicio,
                                          @RequestParam Integer alunoId,
                                          @RequestParam Integer instrutorId,
                                          @RequestParam List<String> exercicios) {
        fichaDeExercicioService.criarFichaDeExercicio(alunoId, instrutorId, exercicios);
        return "redirect:/home";
    }
    
    @GetMapping("/fichas")
    public String listarFichasDeExercicio(Model model) {
        List<FichaDeExercicio> fichas = fichaDeExercicioService.listarFichasDeExercicio();
        model.addAttribute("fichas", fichas);
        return "Read/ReadFichas";  // Página que lista as fichas (nome da view)
    }

    // Exibe os detalhes de uma ficha de exercício específica
    @GetMapping("/ficha/{id}")
    public String detalhesFichaDeExercicio(@PathVariable Integer id, Model model) {
        FichaDeExercicio fichaDeExercicio = fichaDeExercicioService.buscarFichaDeExercicioDetalhada(id);
        model.addAttribute("fichaDeExercicio", fichaDeExercicio);
        return "Read/ReadDetalhesFichaDeExercicio";  // Página que mostra os detalhes da ficha (nome da view)
    }
    // Exibir exercícios de uma ficha
    
    
    
    
    
    
}
