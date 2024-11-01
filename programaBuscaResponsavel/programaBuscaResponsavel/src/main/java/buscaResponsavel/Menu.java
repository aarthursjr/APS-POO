package buscaResponsavel;

import java.io.IOException;
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
            limparTela();
            System.out.println("|================ MENU DE OPÇÕES =================|");
            System.out.println("| 1 - Buscar Aluno                                |");
            System.out.println("| 2 - Registro Responsável e Alunos               |");
            System.out.println("| 3 - Listagem de todos os Alunos                 |");
            System.out.println("| 4 - Remover Aluno                               |");
            System.out.println("| 5 - Encerrar Programa                           |");
            System.out.println("|=================================================|");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  

            limparTela();

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
                    removerAluno();
                    break;
                case 5:
                    System.out.println("\nEncerrando programa...\n");
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.\n");
            }
        } while (opcao != 5);  

        scanner.close();
    }

    private void buscarAluno() {
        System.out.print("Digite o ID do responsável: ");
        String id = scanner.nextLine();
    
        Responsavel responsavel = sistemaEscola.buscarResponsavelPorId(id);
    
        if (responsavel != null) {
            System.out.print("O nome do responsável é \"" + responsavel.getNome() + "\"? (sim/não): ");
            String resposta = scanner.nextLine();
    
            if (resposta.equalsIgnoreCase("sim") || resposta.equalsIgnoreCase("s")) {
                limparTela();
                
                System.out.println("Aluno(s) de " + responsavel.getNome() + ":\n");
                System.out.println("|======================|======|====================================================|");
                System.out.printf("| %-20s | %-4s | %-50s |%n", "NOME", "NOTA", "COMENTÁRIO");
                System.out.println("|======================|======|====================================================|");
                for (Aluno aluno : responsavel.getAlunos()) {
                    Avaliacao avaliacao = aluno.getAvaliacao();
                    Integer nota = avaliacao.getNota();
                    
                    if (nota >= 0) {
                        String nome = aluno.getNome();
                        String comentario = avaliacao.getComentario();

                        System.out.printf("| %-20s | %04d | %-50s |%n", nome, nota, comentario);
                    }
                }
                System.out.println("|======================|======|====================================================|");
            } else {
                System.out.println("Responsável não confirmado.");
            }
        } else {
            System.out.println("ID inválido.");
        }

        retornarAoMenu();
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
            if (resposta.equalsIgnoreCase("sim") || resposta.equalsIgnoreCase("s")) {
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

            if (!resposta.equalsIgnoreCase("sim") && !resposta.equalsIgnoreCase("s")) {
                adicionarMaisAlunos = false;
            }
        }

        sistemaEscola.adicionarResponsavel(novoResponsavel);
        System.out.println("Responsável e alunos registrados com sucesso!\n");

        retornarAoMenu();
    }

    private void listarTodosAlunos() {
        sistemaEscola.listarTodosAlunos();

        retornarAoMenu();
    }


    private void removerAluno() {
        System.out.print("Digite o ID do responsável: ");
        String idResponsavel = scanner.nextLine();
        
        Responsavel responsavel = sistemaEscola.buscarResponsavelPorId(idResponsavel);
        
        if (responsavel != null) {
            System.out.println("Alunos registrados para o responsável " + responsavel.getNome() + ":");
            for (Aluno aluno : responsavel.getAlunos()) {
                System.out.println("- " + aluno.getNome());
            }
            
            System.out.print("Digite o nome do aluno a ser removido: ");
            String nomeAluno = scanner.nextLine();
            
            boolean removido = responsavel.removerAluno(nomeAluno);
            if (removido) {
                System.out.println("Aluno removido com sucesso.");
            } else {
                System.out.println("Aluno não encontrado.");
            }
        } else {
            System.out.println("Responsável não encontrado.");
        }

        retornarAoMenu();
    }

    private void limparTela() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    private void retornarAoMenu() {
        System.out.println("\nPressione qualquer tecla para retornar ao menu.");
        scanner.nextLine();
    }
}