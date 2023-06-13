package aplicacao;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import entidades.Condutor;
import entidades.Constantes;
import entidades.Pessoa;

public class Programa {
	
	public static LocalDate checarData(Scanner scan) {
		LocalDate data;
		// O o objeto "fmt" permitirá o usuário cadastrar datas no formato numérico dia/mes/ano ou dia-mes-ano,
		// possibilitando também colocar dias e meses com 1 ou 2 dígitos. Mas o ano deve possuir 4 dígitos.
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("[d/M/yyyy][d-M-yyyy]");
		// Loop "infinito" que será quebrado através do return.
		do {
			try {
				System.out.print("Digite a data em formato numérico \"dd/mm/aaaa\" ou \"dd-mm-aaaa\": ");
				// A data de nascimento será lida no formato String e logo em seguida convertida para o formato
				// LocalDate.
				data = LocalDate.parse(scan.nextLine(), fmt);
				// Se a data estiver no formato correto, a função retornará a data para o programa principal,
				// encerrando o "loop infinito".
				return data;
			} catch (Exception e) {
				// Caso a data estiver no formato errado, Java irá produzir um erro. O erro será capturado pelo
				// try catch, permitindo que o programa continue executando. Mas o return não será executado e
				// o loop infinito irá continuar até que o usuário entre com a data em um formato válido.
				System.out.println("Formato de data inválido!");
			}
		} while (true);
	}
	
	public static char checarCategoria(Scanner scan) {
		// Esta função será usada para checar a validade da categoria da CNH, tanto no cadastro de um novo registro
		// quanto na edição de um registro já existente. Os tipos de categoria válidos são A, B, C, D e E.
		char categoria;
		do {
			// O usuário deve digitar uma opção válida de categoria (permitindo a entrada de letras minúsculas,
			// pois serão convertidas para maiúsculas através do toUpperCase). Caso a categoria esteja errada, o
			// do-while continuará executando até que o usuário insira uma opção válida.
			System.out.print("Digite a categoria da CNH (A/B/C/D/E): ");
			categoria = scan.nextLine().toUpperCase().charAt(0);
			if (categoria != 'A' && categoria != 'B' && categoria != 'C' && categoria != 'D' && categoria != 'E') {
				System.out.println("Opção inválida!");
			}
		} while (categoria != 'A' && categoria != 'B' && categoria != 'C' && categoria != 'D' && categoria != 'E');
		return categoria;
	}
	
	public static String checarTipoDeCarteira(Scanner scan) {
		// Esta função será usada para checar a validade do tipo de CNH, tanto no cadastro de um novo registro
		// quanto na edição de um registro já existente. Os tipos de CNH válidos são "DEFINITIVA" e "PPD".
		String tipoDeCarteira;
		do {
			// Aqui o usuário do sistema vai escolher se o registro será de uma PPD ou de uma CNH definitiva
			// (permitindo a entrada de letras minúsculas, pois serão convertidas para maiúsculas através do
			// toUpperCase). Caso o usuário digitar uma opção não-prevista, o do-while continuará executando
			// até que o usuário insira uma opção válida.
			System.out.println("Digite \"PPD\" caso a carteira seja do tipo permissão para dirigir.");
			System.out.println("Digite \"Definitiva\" caso a carteira seja do tipo definitiva.");
			System.out.print("Digite a sua opção: ");
			tipoDeCarteira = scan.nextLine().toUpperCase();
			if (!tipoDeCarteira.equals("PPD") && !tipoDeCarteira.equals("DEFINITIVA")) {
				System.out.println("Opção inválida!");
			}
		} while (!tipoDeCarteira.equals("PPD") && !tipoDeCarteira.equals("DEFINITIVA"));
		return tipoDeCarteira;
	}
	
	public static int buscaDeRegistro(String cpf, List<Pessoa> listaDePessoas) {
		// Esta função irá verificar os CPFs de cada registro na lista através de um For Each, usando o Getter
		// da superclasse Pessoa, e o método .equals() para comparar os dados do tipo String.
		for (Pessoa pessoa: listaDePessoas) {
			if (pessoa.getCpf().equals(cpf)) {
				// Caso encontre o CPF no registro, irá retornar o índice da lista onde o objeto se encontra,
				// e a função termina aqui.
				return listaDePessoas.indexOf(pessoa);
			}
		}
		// Caso não encontre o CPF no registro, o return acima nunca será executado, restando então apenas
		// este return abaixo, valor este que não é possível obter no processo acima. Este valor é usado
		// para informar posteriormente que o CPF não foi encontrado.
		return -1;
	}
	
	public static void imprimirTabela(List<Pessoa> listaDePessoas) {
		// Esta função irá imprimir a tabela com 3 atributos dos objetos: nome, CPF e número da CNH. Esta função
		// será executada na listagem do registro e também na listagem do registro em ordem alfabética.
		// Estas constantes serão utilizadas para auxiliar na formatação da tabelas com a listagem de objetos
		// cadastrados.
		final int formatacaoNome = Constantes.formatacaoNome();
		final int formatacaoCpf = Constantes.formatacaoCpf();
		final int formatacaoRegistro = Constantes.formatacaoRegistro();
		// A String linhaDaTabela vai produzir as linhas horizontais das tabelas, para melhorar a estética da tabela.
		final String linhaDaTabela = Constantes.linhaDaTabela();
		System.out.println(linhaDaTabela);
		// Os valores numéricos das constantes vão ajudar a completar as "células" da tabela com espaços vazios.
		// O padrão do .format ao "completar" um string é posicionar o texto no lado direito.
		// O sinal - em %-s vai posicionar o texto no lado esquerdo.
		System.out.println(String.format("| %-"+formatacaoNome+"s | %-"+formatacaoCpf+"s | %-"+formatacaoRegistro+"s |", "NOME", "CPF", "NÚMERO DE REGISTRO"));
		System.out.println(linhaDaTabela);
		// O For Each vai passar por cada objeto cadastrado no registro, e executar o resumo do objeto.
		for (Pessoa pessoa : listaDePessoas) {
			System.out.println(pessoa.resumo());
		}
		System.out.println(linhaDaTabela);
	}


	public static void main(String[] args) {
		
		// Os objetos serão armazenados em um ArrayList, do tipo superclasse Pessoa.
		List<Pessoa> listaDePessoas = new ArrayList<>();
		
		// O programa irá cadastrar automaticamente 7 objetos, de acordo com um dos requisitos do trabalho.
		listaDePessoas.add(new Condutor("João", "12345678901", LocalDate.of(2000, 1, 1),
				"11111111111", 'B', LocalDate.of(2022, 1, 1), "DEFINITIVA"));
		listaDePessoas.add(new Condutor("Maria", "98765432109", LocalDate.of(2005, 1, 1),
				"22222222222", 'A', LocalDate.of(2023, 1, 1), "DEFINITIVA"));
		listaDePessoas.add(new Pessoa("Pedro", "29137219371", LocalDate.of(1987, 11, 23)));
		listaDePessoas.add(new Condutor("José", "61823689126", LocalDate.of(1995, 2, 28),
				"44444444444", 'E', LocalDate.of(2019, 11, 20), "PPD"));
		listaDePessoas.add(new Condutor("Lúcia", "68236812735", LocalDate.of(1992, 2, 29),
				"55555555555", 'B', LocalDate.of(2020, 3, 17), "PPD"));
		listaDePessoas.add(new Pessoa("Júlia", "02713290372", LocalDate.of(2003, 12, 25)));
		listaDePessoas.add(new Condutor("Eduardo", "76914609219", LocalDate.of(1953, 7, 31),
				"77777777777", 'A', LocalDate.of(2020, 9, 11), "DEFINITIVA"));
		
		Scanner scan = new Scanner(System.in);
		
		// Esta variável será utilizada para "navegar" no menu textual do progorama.
		String opcaoDoMenu;
		// Esta variável será utilizada nas opções buscar, editar e deletar, que dependem da função buscaDeRegistro.
		int indiceDoRegistro;
		// Caso o usuário queira cadastrar novos objetos no registro, estas variáveis serão utilizadas para
		// auxiliar neste processo.
		String nome, cpf, numeroDeRegistro, tipoDeCarteira;
		char categoria;
		LocalDate dataDeNascimento, dataDeEmissao;
		// No processo de edição de objetos, será necessário realizar downcasting. O objeto declarado abaixo irá
		// auxiliar nesse processo.
		Condutor condutor;
		
		System.out.println("Seja bem-vindo ao sistema de cadastro do DETRAN.");
		// O loop do-while permitirá a execução do programa até que o usuário digite a opção para encerrar o
		// programa.
		do {
			System.out.println("\nMENU PRINCIPAL");
			System.out.println("Digite 1 para cadastrar uma novo registro no sistema.");
			System.out.println("Digite 2 para buscar um registro no sistema.");
			System.out.println("Digite 3 para buscar e editar um registro no sistema.");
			System.out.println("Digite 4 para buscar e deletar um registro no sistema.");
			System.out.println("Digite 5 para listar todos os registros do sistema.");
			System.out.println("Digite 6 para listar todos os registros do sistema em ordem alfabética.");
			System.out.println("Digite 7 para encerrar o programa.");
			System.out.print("Digite a sua opção: ");
			opcaoDoMenu = scan.nextLine();
			// O if-else irá executar as funções de acordo com o que o usuário digitou.
			if (opcaoDoMenu.equals("1")) {
				// Esta opção vai permitir cadastrar novos objetos.
				System.out.println("\nCadastro de um novo registro:");
				System.out.print("Digite o nome: ");
				nome = scan.nextLine();
				System.out.print("Digite o CPF: ");
				cpf = scan.nextLine();
				System.out.print("Data de nascimento. ");
				// A função checarData vai usar o objeto scan para obter do usuário a data de nascimento.
				// Se a data estiver em um formato válido, irá retornar a data na forma de LocalDate.
				dataDeNascimento = checarData(scan);
				do {
					// Aqui o usuário do sistema vai escolher se o registro será de um condutor habilitado ou um cidadão
					// não-habilitado. Se o usuário digitar uma opção não-prevista, o do-while continuará executando até
					// que o usuário insira uma opção válida.
					System.out.println("Digite 1 caso o cidadão NÃO possua a Carteira Nacional de Habilitação.");
					System.out.println("Digite 2 caso o cidadão possua a Carteira Nacional de Habilitação.");
					System.out.print("Digite a sua opção: ");
					opcaoDoMenu = scan.nextLine();
					if (!opcaoDoMenu.equals("1") && !opcaoDoMenu.equals("2")) {
						System.out.println("Opção inválida!");
					}
				} while (!opcaoDoMenu.equals("1") && !opcaoDoMenu.equals("2"));
				if (opcaoDoMenu.equals("1")) {
					// Cidadãos não-habilitados serão armazenados na lista na forma de um objeto da classe "Pessoa".
					listaDePessoas.add(new Pessoa(nome, cpf, dataDeNascimento));
				} else {
					// Aqui serão requisitados as informações específicas sobre condutores habilitados, que possuem uma CNH.
					System.out.print("Digite o número do registro da CNH: ");
					numeroDeRegistro = scan.nextLine();
					// A função checarCategoria vai usar o objeto scan para obter do usuário a categoria da CNH.
					// Se for uma opção válida, irá retornar a categoria (com letra maiúscula).
					categoria = checarCategoria(scan);
					// A data de emissão será lida no formato String e logo em seguida convertida para o formato LocalDate.
					System.out.print("Data de emissão. ");
					// A função checarData vai usar o objeto scan para obter do usuário a data de emissão.
					// Se a data estiver em um formato válido, irá retornar a data na forma de LocalDate.
					dataDeEmissao = checarData(scan);
					// A função checarTipoDeCarteira vai usar o objeto scan para obter do usuário o tipo de CNH.
					// Se for uma opção válida, irá retornar o tipo de CNH (com letras maiúsculas).
					tipoDeCarteira = checarTipoDeCarteira(scan);
					// Condutores habilitados serão armazenados na lista na forma de um objeto da classe "Condutor".
					listaDePessoas.add(new Condutor(nome, cpf, dataDeNascimento,
							numeroDeRegistro, categoria, dataDeEmissao, tipoDeCarteira));
				}
				System.out.println("Cadastro realizado com sucesso.");
			} else if (opcaoDoMenu.equals("2")) {
				// Esta opção vai permitir buscar registros específicos a partir de um dos seus atributos: o CPF.
				System.out.println("\nBusca de registro no sistema:");
				System.out.print("Digite o número do CPF do cidadão: ");
				cpf = scan.nextLine();
				// A busca será executada na função "buscaDeRegistro", que retornará o índice do objeto na lista.
				indiceDoRegistro = buscaDeRegistro(cpf, listaDePessoas);
				if (indiceDoRegistro == -1) {
					// Se a função retornar o valor -1, isso indica que a função passou pela lista inteira e não
					// encontrou o objeto com o CPF informado.
					System.out.println("O CPF " + cpf + " não foi encontrado no registro.");
				} else {
					// Caso contrário, o sysout abaixo irá informar o usuário todas as informações do objeto,
					// usando o .get() para obter o objeto com base no índice da lista, e o método .completo() para
					// produzir o String com todas as informações sobre o objeto.
					System.out.println("CPF encontrado!\nListagem completa do cidadão de CPF " + cpf + ":\n" + listaDePessoas.get(indiceDoRegistro).completo());
				}
			} else if (opcaoDoMenu.equals("3")) {
				// Esta opção vai permitir editar um registro específico da lista.
				// Primeiro será feito uma busca através do CPF.
				System.out.println("\nBuscar e editar um registro do sistema:");
				System.out.print("Digite o número do CPF do cidadão: ");
				cpf = scan.nextLine();
				// A busca será executada na função "buscaDeRegistro", que retornará o índice do objeto na lista.
				indiceDoRegistro = buscaDeRegistro(cpf, listaDePessoas);
				if (indiceDoRegistro == -1) {
					// Se a função retornar o valor -1, isso indica que a função passou pela lista inteira e não
					// encontrou o objeto com o CPF informado.
					System.out.println("O CPF " + cpf + " não foi encontrado no registro.");
				} else {
					System.out.println("CPF encontrado!");
					// Como estamos trabalhando com duas classes diferentes com atributos diferentes, usou-se o operador
					// instanceof para identificar o tipo de objeto que será editado. Objetos do tipo Condutor vão permitir
					// a edição de um dos 7 atributos, enquanto não-condutores só possuem 3 atributos para editar.
					if (listaDePessoas.get(indiceDoRegistro) instanceof Condutor) {
						// Os objetos obtidos do arrayList são, por padrão, do tipo Pessoa (pois o arrayList foi declarado como tal),
						// o que dificulta o acesso aos setters da classe Condutor. Para resolver este problema, faz-se um downcasting
						// do objeto da classe Condutor, para que o Java saiba explicitamente o tipo de classe desse objeto obtido
						// através da lista.
						condutor = (Condutor)listaDePessoas.get(indiceDoRegistro);
						do {
							// Aqui o usuário vai escolher qual dos 7 atributos vai editar. Se o usuário digitar uma opção
							// não-prevista, o do-while continuará executando até que o usuário insira uma opção válida.
							System.out.println("Digite 1 caso queira editar o nome.");
							System.out.println("Digite 2 caso queira editar o CPF.");
							System.out.println("Digite 3 caso queira editar a data de nascimento.");
							System.out.println("Digite 4 caso queira editar o número de registro.");
							System.out.println("Digite 5 caso queira editar a categoria.");
							System.out.println("Digite 6 caso queira editar a data de emissão.");
							System.out.println("Digite 7 caso queira editar o tipo de carteira.");
							System.out.print("Digite a sua opção: ");
							opcaoDoMenu = scan.nextLine();
							// Caso o usuário digite uma opção válida, o atributo escolhido será editado através dos setters.
							if (opcaoDoMenu.equals("1")) {
								System.out.print("Editando o nome. Digite o novo nome: ");
								condutor.setNome(scan.nextLine());
							} else if (opcaoDoMenu.equals("2")) {
								System.out.print("Editando o CPF. Digite o novo CPF: ");
								condutor.setCpf(scan.nextLine());
							} else if (opcaoDoMenu.equals("3")) {
								System.out.print("Editando a data de nascimento. ");
								// A função checarData vai usar o objeto scan para obter do usuário a data de nascimento.
								// Se a data estiver em um formato válido, irá retornar a data na forma de LocalDate.
								condutor.setDataDeNascimento(checarData(scan));
							} else if (opcaoDoMenu.equals("4")) {
								System.out.print("Editando o número de registro. Digite o novo número de registro: ");
								condutor.setNumeroDeRegistro(scan.nextLine());
							} else if (opcaoDoMenu.equals("5")) {
								System.out.print("Editando a categoria. ");
								// A função checarCategoria vai usar o objeto scan para obter a categoria da CNH do usuário do sistema.
								// Se for uma opção válida, irá retornar a categoria (com letra maiúscula).
								condutor.setCategoria(checarCategoria(scan));
							} else if (opcaoDoMenu.equals("6")) {
								System.out.print("Editando a data de emissão. ");
								// A função checarData vai usar o objeto scan para obter do usuário a data de emissão.
								// Se a data estiver em um formato válido, irá retornar a data na forma de LocalDate.
								condutor.setDataDeEmissao(checarData(scan));
							} else if (opcaoDoMenu.equals("7")) {
								System.out.print("Editando o tipo de carteira. ");
								// A função checarTipoDeCarteira vai usar o objeto scan para obter do usuário o tipo de CNH.
								// Se for uma opção válida, irá retornar o tipo de CNH (com letras maiúsculas).
								condutor.setTipoDeCarteira(checarTipoDeCarteira(scan));
							} else {
								System.out.println("Opção inválida!");
							}
						} while (!opcaoDoMenu.equals("1") && !opcaoDoMenu.equals("2") && !opcaoDoMenu.equals("3") && !opcaoDoMenu.equals("4") && !opcaoDoMenu.equals("5") && !opcaoDoMenu.equals("6") && !opcaoDoMenu.equals("7"));
					} else {
						// Aqui será trabalhado com objetos da classe-pai Pessoa, portanto não é necessário fazer downcasting.
						do {
							// Aqui o usuário vai escolher qual dos 3 atributos vai editar. Se o usuário digitar uma opção
							// não-prevista, o do-while continuará executando até que o usuário insira uma opção válida.
							System.out.println("Digite 1 caso queira editar o nome.");
							System.out.println("Digite 2 caso queira editar o CPF.");
							System.out.println("Digite 3 caso queira editar a data de nascimento.");
							System.out.print("Digite a sua opção: ");
							opcaoDoMenu = scan.nextLine();
							// Caso o usuário digite uma opção válida, o atributo escolhido será editado através dos setters.
							if (opcaoDoMenu.equals("1")) {
								System.out.print("Editando o nome. Digite o novo nome: ");
								listaDePessoas.get(indiceDoRegistro).setNome(scan.nextLine());
							} else if (opcaoDoMenu.equals("2")) {
								System.out.print("Editando o CPF. Digite o novo CPF: ");
								listaDePessoas.get(indiceDoRegistro).setCpf(scan.nextLine());
							} else if (opcaoDoMenu.equals("3")) {
								System.out.print("Editando a data de nascimento. ");
								// A função checarData vai usar o objeto scan para obter do usuário a data de nascimento.
								// Se a data estiver em um formato válido, irá retornar a data na forma de LocalDate.
								listaDePessoas.get(indiceDoRegistro).setDataDeNascimento(checarData(scan));
							} else {
								System.out.println("Opção inválida!");
							}
						} while (!opcaoDoMenu.equals("1") && !opcaoDoMenu.equals("2") && !opcaoDoMenu.equals("3"));
					}
					System.out.println("Operação de edição de registro concluída.");
				}
			} else if (opcaoDoMenu.equals("4")) {
				// Esta opção vai permitir deletar um registro específico da lista.
				// Similarmente, primeiro é feito uma busca através do CPF.
				System.out.println("\nBuscar e deletar um registro do sistema:");
				System.out.print("Digite o número do CPF do cidadão: ");
				cpf = scan.nextLine();
				// A busca será executada na função "buscaDeRegistro", que retornará o índice do objeto na lista.
				indiceDoRegistro = buscaDeRegistro(cpf, listaDePessoas);
				if (indiceDoRegistro == -1) {
					// Se a função retornar o valor -1, isso indica que a função passou pela lista inteira e não
					// encontrou o objeto com o CPF informado.
					System.out.println("O CPF " + cpf + " não foi encontrado no registro.");
				} else {
					System.out.println("CPF encontrado!\nO registro do cidadão de CPF " + cpf + " será deletado permanentemente do sistema.");
					do {
						// Aqui o usuário do sistema vai confirmar se o registro deve ser deletado ou não.
						// Se o usuário digitar uma opção não-prevista, o do-while continuará executando até que o usuário
						// insira uma opção válida.
						System.out.println("Digite 1 caso queira prosseguir com a operação.");
						System.out.println("Digite 2 caso queira interromper a operação.");
						System.out.print("Digite a sua opção: ");
						opcaoDoMenu = scan.nextLine();
						if (opcaoDoMenu.equals("1")) {
							// Uma vez confirmado, o .remove() irá deletar o objeto da lista com base no seu índice.
							listaDePessoas.remove(indiceDoRegistro);
							System.out.println("Operação concluida. O registro do cidadão de CPF " + cpf + " foi deletado.");
						} else if (opcaoDoMenu.equals("2")) {
							// Se o usuário não confirmar, a lista não será modificada.
							System.out.println("Operação interrompida.");
						} else {
							System.out.println("Opção inválida!");
						}
					} while (!opcaoDoMenu.equals("1") && !opcaoDoMenu.equals("2"));
				}
			} else if (opcaoDoMenu.equals("5")) {
				// Esta opção vai listar todos os cadastros no registro com apenas 3: o nome, o CPF e o número da CNH.
				System.out.println("\nListagem resumida de todos os registros no sistema:");
				imprimirTabela(listaDePessoas);
			} else if (opcaoDoMenu.equals("6")) {
				// Esta opção vai listar todos os cadastros no registro em ordem alfabética.
				// Para preservar a lista original, criamos uma cópia independente da lista, que será ordenada logo em seguida.
				List<Pessoa> listaOrdemAlfabetica = new ArrayList<>(listaDePessoas);
				// Para ordenar a lista de objetos, usamos o método sort(). Porém somente este método não é suficiente para
				// ordenar de acordo com um atributo específico (o nome).
				// Em seguida, usamos o método comparing(), que permite comparar partes específicas dos objetos (no caso, o atributo
				// nome) antes de ordenar os objetos de fato. A função lambda "pessoa -> pessoa.getNome()" vai obter o nome
				// de cada objeto da lista.
				// Como estamos trabalhando com nomes, surgem algumas dificuldades. Uma delas são as letras maiúsculas, que
				// interferem na comparação. Por isso usamos toLowerCase() para que todas as letras estejam minúsculas no
				// momento da comparação (não vai alterar os dados originais, é apenas para fins de comparação).
				// Por fim, a última dificuldade são os acentos dos caracteres, que também interferem na comparação e ordenação.
				// O método normalize() nos ajuda a substituir caracteres com acentos por caracteres sem acento (não vai alterar os
				// dados originais, é apenas para fins de comparação).  
				listaOrdemAlfabetica.sort(Comparator.comparing(pessoa -> Normalizer.normalize(pessoa.getNome().toLowerCase(), Normalizer.Form.NFD)));
				System.out.println("\nListagem resumida de todos os registros no sistema em ordem alfabética:");
				imprimirTabela(listaOrdemAlfabetica);				
			} else if (opcaoDoMenu.equals("7")) {
				// Esta opção vai encerrar o programa, quebrando o do-while.
				System.out.println("\nEncerrando programa...");
			} else {
				// Caso o usuário digite algo que não foi previsto, ele será informado. O programa vai voltar para o menu principal.
				System.out.println("\nOpção inválida!");
			}
			if (!opcaoDoMenu.equals("7")) {
				// O nextLine abaixo irá "pausar" o programa após cada operação (exceto quando encerrar o programa),
				// para melhorar a experiência do usuário.
				System.out.println("\nPressione a tecla \"Enter\" para voltar ao menu principal...");
				scan.nextLine();
			}
		// O do-while será quebrado caso o usuário digite a opção para encerrar o programa.
		} while (!opcaoDoMenu.equals("7"));
		
		// Fechamento do objeto do tipo Scanner, por sugestão do IDE Eclipse.
		scan.close();
		System.out.println("Programa encerrado com sucesso.");
	}

}