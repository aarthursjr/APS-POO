import java.util.ArrayList;

public class Responsavel {
    private String nome;
    private String id;
    private ArrayList<Aluno> alunos;

    public Responsavel(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.alunos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    @Override
    public String toString() {
        return "Responsável: " + nome + " (ID: " + id + ")";
    }

}
