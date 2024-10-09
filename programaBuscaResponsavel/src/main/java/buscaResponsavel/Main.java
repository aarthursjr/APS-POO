package buscaResponsavel;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


public class Main {
    public static void main(String[] args) {
        try{
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        SistemaEscola sistemaEscola = new SistemaEscola();
        Menu menu = new Menu(sistemaEscola);
        menu.exibirMenu();
        } catch (UnsupportedEncodingException e) {
            System.err.println("Erro ao configurar a codificação de saída: " + e.getMessage());
        }
    }
}
