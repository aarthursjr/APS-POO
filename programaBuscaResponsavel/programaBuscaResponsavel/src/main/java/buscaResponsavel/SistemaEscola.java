package buscaResponsavel;

import java.util.ArrayList;

public class SistemaEscola {
    private ArrayList<Responsavel> responsaveis;

    public SistemaEscola() {
        this.responsaveis = new ArrayList<>();
    }

    public void adicionarResponsavel(Responsavel responsavel) {
        responsaveis.add(responsavel);
    }

    public Responsavel buscarResponsavelPorId(String id) {
        for (Responsavel responsavel : responsaveis) {
            if (responsavel.getId().equals(id)) {
                return responsavel;
            }
        }
        return null;
    }

}