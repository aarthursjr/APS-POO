package buscaResponsavel;
import java.time.LocalDateTime;
import java.time.format.*;

public class Avaliacao {
    private Integer nota;
    private String comentario;
    private LocalDateTime data;

    public Avaliacao() {
        this.nota = -1;
        this.comentario = "";
        this.data = LocalDateTime.now();
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public String getStringData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return getData().format(formatter);
    }

    public Integer getNota() {
        return this.nota;
    }

    public String getComentario() {
        return this.comentario;
    }
}