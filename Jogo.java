import java.util.Scanner;

public class Jogo {
    private Scanner sc;
    private Arma arma;

    public Jogo(Scanner sc) {
        this.sc = sc;
    }
    public Jogo(Scanner sc, Arma arma) {
        this.sc = sc;
        this.arma = arma;
    }

    public void comecar() {
        System.out.println("\n=== Criação do Personagem ===");
        System.out.print("Digite o nome do seu herói: ");
        String nome = sc.nextLine();

        System.out.println("O herói " + nome + " foi criado com sucesso!");

        if (arma != null) {
            System.out.println("Ele começará sua jornada equipado com: " + arma);
        }
    }
}
