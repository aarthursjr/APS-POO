package buscaResponsavel;
import java.time.LocalDateTime;

public class Aluno extends Pessoa {
    private Avaliacao avaliacao;

    public Aluno(String nome, Avaliacao avaliacao) {
        super(nome);
        this.avaliacao = avaliacao;
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
}