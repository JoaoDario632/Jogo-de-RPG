import java.util.Scanner;

public class Jogo {
    private Scanner sc;

    public Jogo(Scanner sc) {
        this.sc = sc;
    }

    public void comecar() {
        System.out.println("\n=== Criação do Personagem ===");
        System.out.print("Digite o nome do seu herói: ");
        String nome = sc.nextLine();
        System.out.println("O herói " + nome + " foi criado com sucesso!");
    }
}