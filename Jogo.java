import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Scanner sc;
    private Arma arma;
    private Armadura armadura;
    private Pocao pocaoJogador;
    private int placar = 0;
    private Random random = new Random();
    private int experiencia = 0;
    private int nivel = 1;
    private int vidainicial = 50;

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
        System.out.println("Nível final: " + nivel + " | Experiência total: " + experiencia);
    }

    private void jogarFases(String nome) {
        String[] inimigos = {"Goblin", "Orc", "Dragão"};
        int[] vidaInimigo = {20, 35, 60};
        int[] danoInimigo = {5, 8, 12};

        int vidaHeroi = vidainicial + armadura.getConstanteDefesa();

        for (int fase = 0; fase < inimigos.length; fase++) {
            Pocao pocaoInimigo = Pocao.gerarPocaoAleatoria();
            System.out.println("\n=== Fase " + (fase + 1) + " ===");
            System.out.println("Um " + inimigos[fase] + " aparece!");
            System.out.println("O inimigo possui a poção: " + pocaoInimigo.getNome());
            int vidaMonstro = vidaInimigo[fase];
            boolean pocaoUsada = false;

            while (vidaMonstro > 0 && vidaHeroi > 0) {
                System.out.println("\n❤️ Sua vida: " + vidaHeroi + " | 🧟 Vida do " + inimigos[fase] + ": " + vidaMonstro);
                System.out.println("⚔️ 1. Atacar");
                System.out.println("🛡️ 2. Defender");
                System.out.println("🧪 3. Usar poção");
                System.out.print("Escolha sua ação: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Entrada inválida! Digite um número.");
                    sc.nextLine();
                    continue;
                }
                int escolha = sc.nextInt();
                sc.nextLine();

                if (escolha == 1) {
                    int dano = arma.getConstanteDano();
                    // Chance de golpe crítico
                    if (random.nextInt(100) < 15) {
                        dano *= 2;
                        System.out.println("💥 Golpe crítico! Dano dobrado!");
                    }
                    System.out.println("Você ataca com sua " + arma.getNome() + " e causa " + dano + " de dano!");
                    vidaMonstro -= dano;
                } else if (escolha == 2) {
                    System.out.println("Você se defende!");
                    int danoRecebido = Math.max(0, danoInimigo[fase] - armadura.getConstanteDefesa());
                    vidaHeroi -= danoRecebido;
                    System.out.println("O ataque inimigo causa " + danoRecebido + " de dano reduzido.");
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
                System.out.println("\n💀 Você foi derrotado pelo " + inimigos[fase] + "!");
                break;
            } else {
                System.out.println("\n🏆 Você derrotou o " + inimigos[fase] + "!");
                placar += 100 * (fase + 1);

                // Ganha experiência
                int xpGanho = 80 * (fase + 1);
                experiencia += xpGanho;
                System.out.println("✨ Você ganhou " + xpGanho + " de experiência!");

                // Verifica se sobe de nível
                subirDeNivel();

                // Recupera um pouco da vida antes da próxima fase
                vidaHeroi = Math.min(vidaHeroi + 10, vidainicial + armadura.getConstanteDefesa());
                System.out.println("Você descansou e recuperou 10 pontos de vida.");
            }
        }
    }

    private void subirDeNivel() {
        int xpNecessario = nivel * 100;
        if (experiencia >= xpNecessario) {
            nivel++;
            experiencia -= xpNecessario;
            vidainicial += 10;
            arma.aumentarDano(1);
            armadura.aumentarDefesa(1);
            System.out.println("\n🔼 Parabéns! Você subiu para o nível " + nivel + "!");
            System.out.println("💪 Vida base aumentada para " + vidainicial);
            System.out.println("⚔️ Dano da arma e 🛡️ defesa da armadura melhoraram!");
        }
    }
}
