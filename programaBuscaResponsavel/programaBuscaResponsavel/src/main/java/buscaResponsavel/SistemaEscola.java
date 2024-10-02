package buscaResponsavel;

import java.util.HashMap;
import java.util.Map;

public class SistemaEscola {
    private Map<String, Responsavel> responsaveis;

    public SistemaEscola() {
        this.responsaveis = new HashMap<>();
    }

    public void adicionarResponsavel(Responsavel responsavel) {
        responsaveis.put(responsavel.getId(), responsavel);
    }

    public Responsavel buscarResponsavelPorId(String id) {
        return responsaveis.get(id);
    }

    public void listarTodosAlunos() {
        if (responsaveis.isEmpty()) {
            System.out.println("Nenhum responsável registrado.");
            return;
        }

        System.out.println("Lista de todos os alunos registrados:");

        for (Responsavel responsavel : responsaveis.values()) {
            System.out.println("Responsável: " + responsavel.getNome());
            for (Aluno aluno : responsavel.getAlunos()) {
                System.out.println("- " + aluno.getNome());
            }
        }
    }
}
