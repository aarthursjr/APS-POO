package buscaResponsavel;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Aluno {
    private String nome;
    private Avaliacao avaliacao;

    public Aluno(String nome, Avaliacao avaliacao) {
        this.nome = nome;
        this.avaliacao = avaliacao;
    }

    public String getNome() {
        return nome;
    }

    public Avaliacao getAvaliacao() {
        Avaliacao avaliacao = this.avaliacao;

        if ( validaDataLimite(avaliacao.getData(), 2) == false ) {
            this.avaliacao.setNota(-1);
            this.avaliacao.setComentario("");
        }

        return this.avaliacao;
    }

    private boolean validaDataLimite(LocalDateTime data, Integer qtdDias) {
        if ( data.plusDays(qtdDias).isAfter(LocalDateTime.now()) ) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}