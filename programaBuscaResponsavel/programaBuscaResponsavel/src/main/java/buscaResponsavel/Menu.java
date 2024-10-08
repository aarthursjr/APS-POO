package buscaResponsavel;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {
    private SistemaEscola sistemaEscola;
    private Scanner scanner;

    public Menu(SistemaEscola sistemaEscola) {
        this.sistemaEscola = sistemaEscola;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("|=================MENU DE OPÇÕES==================|");
            System.out.println("|1 - Buscar Aluno                                 |");
            System.out.println("|2 - Registro Responsável e Alunos                |");
            System.out.println("|3 - Listagem de todos os Alunos                  |");
            System.out.println("|4 - Encerrar Programa                            |");
            System.out.println("|=================================================|");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer após a leitura do inteiro

            switch (opcao) {
                case 1:
                    buscarAluno();
                    break;
                case 2:
                    registrarResponsavelEAlunos();
                    break;
                case 3:
                    listarTodosAlunos();
                    break;
                case 4:
                    System.out.println("\nEncerrando programa...\n");
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.\n");
            }
        } while (opcao != 4);  

        scanner.close();
    }

    private void buscarAluno() {
        System.out.print("Digite o ID do responsável: ");
        String id = scanner.nextLine();
    
        Responsavel responsavel = sistemaEscola.buscarResponsavelPorId(id);
    
        if (responsavel != null) {
            System.out.print("O nome do responsável é \"" + responsavel.getNome() + "\"? (sim/não): ");
            String resposta = scanner.nextLine();
    
            if (resposta.equalsIgnoreCase("sim")) {
                System.out.println(" Aluno(s) de " + responsavel.getNome() + ": ");
                for (Aluno aluno : responsavel.getAlunos()) {
                    System.out.println("- " + aluno.getNome());  

                    Avaliacao avaliacao = aluno.getAvaliacao();
                    
                    if (avaliacao.getNota() >= 0) {
                        System.out.println("  - " + avaliacao.getStringData() + ": " + "Nota " + avaliacao.getNota() + " (" + avaliacao.getComentario() + ")");
                    }
                }
            } else {
                System.out.println("Responsável não confirmado.");
            }
        } else {
            System.out.println("ID inválido.");
        }
    }

    private void registrarResponsavelEAlunos() {
        System.out.print("Digite o nome do responsável: ");
        String nomeResponsavel = scanner.nextLine();

        System.out.print("Digite o ID do responsável: ");
        String idResponsavel = scanner.nextLine();

        Responsavel novoResponsavel = new Responsavel(nomeResponsavel, idResponsavel);

        boolean adicionarMaisAlunos = true;
        while (adicionarMaisAlunos) {
            String resposta;
            Avaliacao avaliacao = new Avaliacao();

            System.out.print("Digite o nome do aluno: ");
            String nomeAluno = scanner.nextLine();

            System.out.print("Deseja adicionar uma avaliação para " + nomeAluno + "? (sim/não): ");
            resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                System.out.print("Digite a nota de " + nomeAluno + ": ");
                Integer nota = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Digite um comentário (opcional): ");
                String comentario = scanner.nextLine();

                avaliacao.setNota( nota );
                avaliacao.setComentario( comentario );
                avaliacao.setData(LocalDateTime.now());
            }

            Aluno aluno = new Aluno(nomeAluno, avaliacao);
            novoResponsavel.adicionarAluno(aluno);

            System.out.print("Mais um aluno? (sim/não): ");
            resposta = scanner.nextLine();

            if (!resposta.equalsIgnoreCase("sim")) {
                adicionarMaisAlunos = false;
            }
        }

        sistemaEscola.adicionarResponsavel(novoResponsavel);
        System.out.println("Responsável e alunos registrados com sucesso!\n");
    }

    private void listarTodosAlunos() {
        sistemaEscola.listarTodosAlunos();  
    }
}
