package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
	// A classe Pessoa vai armazenar informações de cidadãos não-habilitados.
	// A classe Pessoa também vai servir de superclasse para a classe Condutor.
	// Os atributos da classe Pessoa são o nome do cidadão, o seu CPF, e a sua data de nascimento.
	private String nome;
	private String cpf;
	private LocalDate dataDeNascimento;
	
	// Construtor padrão para gerar objetos sem passar nenhum argumento.
	public Pessoa() {
	}
	// Construtor para gerar objetos com todos os 3 argumentos.
	public Pessoa(String nome, String cpf, LocalDate dataDeNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
	}
	
	// Getters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public String resumo() {
		// Estas constantes serão utilizadas para auxiliar na formatação da tabelas com a listagem de objetos
		// cadastrados.
		final int formatacaoNome = Constantes.formatacaoNome();
		final int formatacaoCpf = Constantes.formatacaoCpf();
		final int formatacaoRegistro = Constantes.formatacaoRegistro();
		
		// O método .resumo() vai retornar apenas 2 atributos: nome e CPF, e também a informação de que o cidadão "não possui habilitação".
		// Os valores numéricos das constantes vão ajudar a completar as "células" da tabela com espaços vazios.
		// O padrão do .format ao "completar" um string é posicionar o texto no lado direito.
		// O sinal - em %-s vai posicionar o texto no lado esquerdo.
		return String.format("| %-"+formatacaoNome+"s | %-"+formatacaoCpf+"s | %-"+formatacaoRegistro+"s |", nome, cpf, "Não possui habilitação");
	}
	public String completo() {
		// O método .completo() vai retornar todos os atributos relacionados ao cidadão não-habilitado.
		return "NOME: " + nome + " | CPF: " + cpf + " | DATA DE NASCIMENTO: " + dataDeNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nNÃO POSSUI HABILITAÇÃO";
	}
}