package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Condutor extends Pessoa {
	// A classe Condutor vai armazenar informações de condutores habilitados.
	// A classe Condutor herda da superclasse Pessoa.
	// Os atributos herdados da classe Pessoa são o nome do cidadão, o seu CPF, e a sua data de nascimento.
	// Os atributos da classe Condutor são o número de registro da CNH, a categoria da habilitação, e a data da emissão da CNH.
	private String numeroDeRegistro;
	private char categoria;
	private LocalDate dataDeEmissao;
	private LocalDate dataDeValidade;
	private String tipoDeCarteira;
	
	// Construtor padrão para gerar objetos sem passar nenhum argumento.
	public Condutor() {
		// A função super irá chamar o construtor da classe-pai Pessoa, sem passar nenhum argumento.
		super();
	}
	// Construtor para gerar objetos com todos os 5 argumentos.
	public Condutor(String nome, String cpf, LocalDate dataDeNascimento,
			String numeroDeRegistro, char categoria, LocalDate dataDeEmissao, String tipoDeCarteira) {
		// A função super irá chamar o construtor da classe-pai, passando 5 argumentos referenes aos 5 atributos da
		// classe-pai Pessoa.
		super(nome, cpf, dataDeNascimento);
		this.numeroDeRegistro = numeroDeRegistro;
		this.categoria = categoria;
		this.dataDeEmissao = dataDeEmissao;
		// A data de validade da carteira é 10, 5 ou 3 anos após a sua emissão. 
		this.dataDeValidade = dataDeEmissao.plusYears(anosDeValidade());
		this.tipoDeCarteira = tipoDeCarteira;
	}
	
	// Getters e Setters
	public String getNumeroDeRegistro() {
		return numeroDeRegistro;
	}
	public void setNumeroDeRegistro(String numeroDeRegistro) {
		this.numeroDeRegistro = numeroDeRegistro;
	}
	public char getCategoria() {
		return categoria;
	}
	public void setCategoria(char categoria) {
		this.categoria = categoria;
	}
	public LocalDate getDataDeEmissao() {
		return dataDeEmissao;
	}
	public void setDataDeEmissao(LocalDate dataDeEmissao) {
		this.dataDeEmissao = dataDeEmissao;
		// A data de validade da carteira é 10, 5 ou 3 anos após a sua emissão.
		this.dataDeValidade = dataDeEmissao.plusYears(anosDeValidade());
	}
	// Data de validade possui apenas o getter, pois a sua modificação é feita de acordo com a data de emissão,
	// portanto não deve ser modificável fora da classe.
	public LocalDate getDataDeValidade() {
		return dataDeValidade;
	}
	public String getTipoDeCarteira() {
		return tipoDeCarteira;
	}
	public void setTipoDeCarteira(String tipoDeCarteira) {
		this.tipoDeCarteira = tipoDeCarteira;
	}
	
	// Este método vai calcular o número de anos relativo a data de validade da carteira. O método é privado, pois
	// não deve ser acessível fora da classe.
	private int anosDeValidade() {
		// ChronoUnit irá calcular a quantidade de anos entre a data de nascimento e a data atual.
		long idade = ChronoUnit.YEARS.between(super.getDataDeNascimento(), LocalDate.now());
		if (idade < 50) {
			// A data de validade será 10 anos após a emissão caso a idade do condutor seja menor que 50 anos.
			return 10;
		} else if (idade < 69) {
			// A data de validade será 5 anos após a emissão caso a idade do condutor esteja entre 50 e 68 anos.
			return 5;
		} else {
			// A data de validade será 3 anos após a emissão caso a idade do condutor seja maior ou igual a 69 anos.
			return 3;
		}
	}
	
	@Override
	public String resumo() {
		// O método foi sobreposto em relação ao método da classe pai.
		
		// Estas constantes serão utilizadas para auxiliar na formatação da tabelas com a listagem de objetos
		// cadastrados. 
		final int formatacaoNome = Constantes.formatacaoNome();
		final int formatacaoCpf = Constantes.formatacaoCpf();
		final int formatacaoRegistro = Constantes.formatacaoRegistro();
		
		// O método .resumo() vai retornar apenas 3 atributos: nome, CPF e o número da CNH.
		// Os valores numéricos das constantes vão ajudar a completar as "células" da tabela com espaços vazios.
		// O padrão do .format ao "completar" um string é posicionar o texto no lado direito.
		// O sinal - em %-s vai posicionar o texto no lado esquerdo.
		return String.format("| %-"+formatacaoNome+"s | %-"+formatacaoCpf+"s | %-"+formatacaoRegistro+"s |", super.getNome(), super.getCpf(), numeroDeRegistro);
	}
	@Override
	public String completo() {
		// O método foi sobreposto em relação ao método da classe pai.
		
		// O método .completo() vai retornar todos os atributos relacionados ao  condutor habilitado.
		return "NOME: " + super.getNome() + " | CPF: " + super.getCpf() + " | DATA DO NASCIMENTO: " + super.getDataDeNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nNÚMERO DE REGISTRO: " + numeroDeRegistro + " | CATEGORIA: " + categoria + " | DATA DE EMISSÃO: " + dataDeEmissao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))  + " | DATA DE VALIDADE: " + dataDeValidade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | Tipo de carteira: " + tipoDeCarteira;
	}
}