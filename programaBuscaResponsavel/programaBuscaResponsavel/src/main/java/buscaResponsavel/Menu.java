package buscaResponsavel;

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
            System.out.println("\n==============================");
            System.out.println("1 - Buscar Aluno");
            System.out.println("2 - Registrar Responsável e Alunos");
            System.out.println("3 - Encerrar Programa");
            System.out.println("==============================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            

            switch (opcao) {
                case 1:
                    buscarAluno();
                    break;
                case 2:
                    registrarResponsavelEAlunos();
                    break;
                case 3:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

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
                System.out.println(responsavel.getNome() + " veio buscar o(s) aluno(s): ");
                for (Aluno aluno : responsavel.getAlunos()) {
                    System.out.println("- " + aluno.getNome());  
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
            System.out.print("Digite o nome do aluno: ");
            String nomeAluno = scanner.nextLine();
            Aluno aluno = new Aluno(nomeAluno);
            novoResponsavel.adicionarAluno(aluno);

            System.out.print("Mais um aluno? (sim/não): ");
            String resposta = scanner.nextLine();

            if (!resposta.equalsIgnoreCase("sim")) {
                adicionarMaisAlunos = false;
            }
        }

        sistemaEscola.adicionarResponsavel(novoResponsavel);
        System.out.println("Responsável e alunos registrados com sucesso!");
    }
}