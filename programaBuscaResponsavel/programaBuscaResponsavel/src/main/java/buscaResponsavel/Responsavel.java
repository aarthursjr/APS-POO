package buscaResponsavel;
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

    public boolean removerAluno(String nomeAluno) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                alunos.remove(aluno);
                return true; 
            }
        }
        return false; 
    }
    
    public String toString() {  
        return "Responsável: " + nome + " (ID: " + id + ")";
    }

}