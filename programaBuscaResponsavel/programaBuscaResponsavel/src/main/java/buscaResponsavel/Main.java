
package buscaResponsavel;


public class Main {
    public static void main(String[] args) {
        SistemaEscola sistemaEscola = new SistemaEscola();
        Menu menu = new Menu(sistemaEscola);
        menu.exibirMenu();
    }
}
