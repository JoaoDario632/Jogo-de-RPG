import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Scanner sc;
    private Arma arma;
    private Armadura armadura;
    private Pocao pocaoJogador;
    private int placar = 0; 
    private Random random = new Random();

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
            System.out.println("Ele começará sua jornada equipado com: " + arma);
        }

        if (armadura == null) {
            armadura = Armadura.escolherArmadura(sc);
        }
        if (armadura != null) {
            System.out.println("E protegido pela armadura: " + armadura);
        }

        pocaoJogador = Pocao.escolherPocao(sc);
        System.out.println("Sua poção escolhida: " + pocaoJogador.getNome() + " (" + pocaoJogador.getDescricao() + ")");

        System.out.println("\nSua aventura está prestes a começar...");
        jogarFases(nome);

        System.out.println("\n=== Fim do Jogo ===");
        System.out.println("Placar final de " + nome + ": " + placar + " pontos!");
    }

    private void jogarFases(String nome) {
        String[] inimigos = {"Goblin", "Orc", "Dragão"};
        int[] vidaInimigo = {20, 35, 60};
        int[] danoInimigo = {5, 8, 12};

        int vidaHeroi = 50 + armadura.getConstanteDefesa();

        for (int fase = 0; fase < inimigos.length; fase++) {
            Pocao pocaoInimigo = Pocao.gerarPocaoAleatoria();
            System.out.println("\n=== Fase " + (fase + 1) + " ===");
            System.out.println("Um " + inimigos[fase] + " aparece!");
            System.out.println("O inimigo possui a poção: " + pocaoInimigo.getNome());
            int vidaMonstro = vidaInimigo[fase];
            boolean pocaoUsada = false;

            while (vidaMonstro > 0 && vidaHeroi > 0) {
                System.out.println("\nSua vida: " + vidaHeroi + " | Vida do " + inimigos[fase] + ": " + vidaMonstro);
                System.out.println("1. Atacar");
                System.out.println("2. Defender");
                System.out.println("3. Usar poção");
                System.out.print("Escolha sua ação: ");
                int escolha = sc.nextInt();
                sc.nextLine();

                if (escolha == 1) {
                    System.out.println("Você ataca com sua " + arma.getNome() + "!");
                    vidaMonstro -= arma.getConstanteDano();
                } else if (escolha == 2) {
                    System.out.println("Você se defende!");
                    vidaHeroi -= Math.max(0, danoInimigo[fase] - armadura.getConstanteDefesa());
                    continue;
                } else if (escolha == 3 && !pocaoUsada) {
                    vidaHeroi = pocaoJogador.aplicarEfeito(vidaHeroi, arma, armadura);
                    pocaoUsada = true;
                } else if (escolha == 3 && pocaoUsada) {
                    System.out.println("Você já usou sua poção nesta batalha!");
                } else {
                    System.out.println("Você hesitou e perdeu a chance!");
                }

                if (vidaMonstro > 0) {
                   
                    if (random.nextInt(100) < 25) { 
                        vidaMonstro = pocaoInimigo.aplicarEfeitoInimigo(vidaMonstro, danoInimigo);
                    } else {
                        vidaHeroi -= danoInimigo[fase];
                        System.out.println("O " + inimigos[fase] + " ataca! Você perde " + danoInimigo[fase] + " de vida.");
                    }
                }
            }

            if (vidaHeroi <= 0) {
                System.out.println("\nVocê foi derrotado pelo " + inimigos[fase] + "!");
                break;
            } else {
                System.out.println("\nVocê derrotou o " + inimigos[fase] + "!");
                placar += 100 * (fase + 1);
            }
        }
    }
}
