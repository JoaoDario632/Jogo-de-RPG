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
        System.out.println("\n=========================");
        System.out.println("A Lenda do Guerreiro Sem Noção");
        System.out.println("=========================");
        System.out.println("1. Jogar contra monstros");
        System.out.println("2. Jogar contra outro jogador");
        System.out.print("Escolha uma opção: ");

        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 2) {
            jogarContraOutroJogador();
        } else {
            jogarContraMobs();
        }
    }

    private void jogarContraMobs() {
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
                System.out.println("\nSua vida: " + vidaHeroi + " | Vida do " + inimigos[fase] + ": " + vidaMonstro);
                System.out.println("1. Atacar");
                System.out.println("2. Defender");
                System.out.println("3. Usar poção");
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
                    if (random.nextInt(100) < 15) {
                        dano *= 2;
                        System.out.println("Golpe crítico! Dano dobrado!");
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
                System.out.println("\n Você foi derrotado pelo " + inimigos[fase] + "!");
                break;
            } else {
                System.out.println("\nVocê derrotou o " + inimigos[fase] + "!");
                placar += 100 * (fase + 1);

                int xpGanho = 80 * (fase + 1);
                experiencia += xpGanho;
                System.out.println("Você ganhou " + xpGanho + " de experiência!");
                subirDeNivel();

                vidaHeroi = Math.min(vidaHeroi + 10, vidainicial + armadura.getConstanteDefesa());
                System.out.println("Você descansou e recuperou 10 pontos de vida.");
            }
        }
    }

    private void jogarContraOutroJogador() {
        System.out.println("\n=== MODO JOGADOR VS JOGADOR ===");

        System.out.print("\nDigite o nome do Jogador 1: ");
        String nome1 = sc.nextLine();
        Arma arma1 = Arma.escolherArma(sc);
        Armadura armadura1 = Armadura.escolherArmadura(sc);
        Pocao pocao1 = Pocao.escolherPocao(sc);
        int vida1 = vidainicial + armadura1.getConstanteDefesa();
        boolean pocaoUsada1 = false;

        System.out.print("\nDigite o nome do Jogador 2: ");
        String nome2 = sc.nextLine();
        Arma arma2 = Arma.escolherArma(sc);
        Armadura armadura2 = Armadura.escolherArmadura(sc);
        Pocao pocao2 = Pocao.escolherPocao(sc);
        int vida2 = vidainicial + armadura2.getConstanteDefesa();
        boolean pocaoUsada2 = false;

        System.out.println("\n A batalha entre " + nome1 + " e " + nome2 + " vai começar!");

        int turno = 1;
        while (vida1 > 0 && vida2 > 0) {
            System.out.println("\n=== Turno " + turno + " ===");
            System.out.println("hp" + nome1 + ": " + vida1 + " | hp " + nome2 + ": " + vida2);

          
            System.out.println(nome1 + ", sua vez!");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Usar poção");
            System.out.print("Escolha sua ação: ");
            int acao1 = sc.nextInt();
            sc.nextLine();

            int dano1 = 0;
            boolean defendeu1 = false;

            if (acao1 == 1) {
                dano1 = arma1.getConstanteDano();
                if (random.nextInt(100) < 15) {
                    dano1 *= 2;
                    System.out.println("Golpe crítico de " + nome1 + "!");
                }
            } else if (acao1 == 2) {
                defendeu1 = true;
            } else if (acao1 == 3 && !pocaoUsada1) {
                vida1 = pocao1.aplicarEfeito(vida1, arma1, armadura1);
                pocaoUsada1 = true;
            }

            System.out.println(nome2 + ", sua vez!");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Usar poção");
            System.out.print("Escolha sua ação: ");
            int acao2 = sc.nextInt();
            sc.nextLine();

            int dano2 = 0;
            boolean defendeu2 = false;

            if (acao2 == 1) {
                dano2 = arma2.getConstanteDano();
                if (random.nextInt(100) < 15) {
                    dano2 *= 2;
                    System.out.println("Golpe crítico de " + nome2 + "!");
                }
            } else if (acao2 == 2) {
                defendeu2 = true;
            } else if (acao2 == 3 && !pocaoUsada2) {
                vida2 = pocao2.aplicarEfeito(vida2, arma2, armadura2);
                pocaoUsada2 = true;
            }

            if (acao1 == 1 && !defendeu2) vida2 -= Math.max(0, dano1 - armadura2.getConstanteDefesa());
            if (acao2 == 1 && !defendeu1) vida1 -= Math.max(0, dano2 - armadura1.getConstanteDefesa());

            turno++;
        }

        System.out.println("\n🏁 Fim da batalha!");
        if (vida1 > 0 && vida2 <= 0) {
            System.out.println("Meus Parabéns, o" + nome1 + " venceu!");
        } else if (vida2 > 0 && vida1 <= 0) {
            System.out.println("\"Meus Parabéns, o" + nome2 + " venceu!");
        } else {
            System.out.println("Empate! Ambos caíram ao mesmo tempo!");
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
            System.out.println("\nParabéns! Você subiu para o nível " + nivel + "!");
            System.out.println("Vida base aumentada para " + vidainicial);
            System.out.println("Dano da arma e adefesa da armadura melhoraram! com sucesso");
        }
    }
}
