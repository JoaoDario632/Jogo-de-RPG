import java.util.Scanner;

public class Jogo {
    private Scanner sc;
    private Arma arma;
    private Armadura armadura;

    public Jogo(Scanner sc) {
        this.sc = sc;
    }

    public Jogo(Scanner sc, Arma arma) {
        this.sc = sc;
        this.arma = arma;
    }

    public Jogo(Scanner sc, Arma arma, Armadura armadura) {
        this.sc = sc;
        this.arma = arma;
        this.armadura = armadura;
    }

    public void comecar() {
        System.out.println("\n=== Criação do Personagem ===");
        System.out.print("Digite o nome do seu herói: ");
        String nome = sc.nextLine();

        System.out.println("O herói " + nome + " foi criado com sucesso!");

        if (arma == null) {
            arma = Arma.escolherArma(sc);
        }
        if (arma != null) {
            System.out.println("Ele começará sua jornada equipado com a arma: " 
                               + arma.getNome() + " (Dano: " + arma.getConstanteDano() + ")");
        }

        if (armadura == null) {
            armadura = Armadura.escolherArmadura(sc);
        }
        if (armadura != null) {
            System.out.println("E protegido pela armadura: " 
                               + armadura.getNome() + " (Defesa: " + armadura.getConstanteDefesa() + ")");
        }

        System.out.println("\nSua aventura está prestes a começar...");
    }
}
