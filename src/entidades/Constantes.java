package entidades;

public class Constantes {
	// Estas constantes serão utilizadas para auxiliar na formatação da tabelas com a listagem de objetos
	// cadastrados. 
	// Este número foi escolhido para dar espaço suficiente para o nome do cidadão na tabela.
	public static int formatacaoNome() {
		return 32;
	}
	// CPFs possuem 11 digitos.
	public static int formatacaoCpf() {
		return 11;
	}
	// Números da CNH também possuem 11 digitos, mas foi necessário definir um espaçamento maior para caber
	// o texto "Não possui habilitação" para cidadãos não-habilitados.
	public static int formatacaoRegistro() {
		return 22;
	}
	// A String linhaDaTabela é usada para melhorar a estética da tabela. A quantidade de traços que serão
	// utilizados depende das constantes definidas acima. O número 10 é um "ajuste manual", referente às
	// barras verticais da tabela.
	public static String linhaDaTabela() {
		String linha = "";
		for (int i = 0; i < (formatacaoNome()+formatacaoCpf()+formatacaoRegistro()+10); i++) {
			linha += "-";
		}
		return linha;
	}

}
