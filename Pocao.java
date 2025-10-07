import java.util.Random;
import java.util.Scanner;

public class Pocao {
    private String nome;
    private String descricao;
    private String tipo; 

    public Pocao(String nome, String descricao, String tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Pocao escolherPocao(Scanner sc) {
        Pocao[] opcoes = {
            new Pocao("Poção de Cura", "Recupera 20 pontos de vida", "cura"),
            new Pocao("Poção de Força", "Aumenta o dano da arma em +5", "dano"),
            new Pocao("Poção de Pedra", "Aumenta a defesa em +3", "defesa")
        };

        System.out.println("\n=== Escolha sua poção ===");
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println((i + 1) + ". " + opcoes[i].nome + " - " + opcoes[i].descricao);
        }
        System.out.print("Digite o número da poção desejada: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha >= 1 && escolha <= opcoes.length) {
            return opcoes[escolha - 1];
        }
        System.out.println("Escolha inválida! Será atribuída a primeira poção.");
        return opcoes[0];
    }

    public static Pocao gerarPocaoAleatoria() {
        Pocao[] todas = {
            new Pocao("Poção de Cura", "Recupera 20 pontos de vida", "cura"),
            new Pocao("Poção de Força", "Aumenta o dano em +5", "dano"),
            new Pocao("Poção de Pedra", "Aumenta a defesa em +3", "defesa")
        };
        return todas[new Random().nextInt(todas.length)];
    }

    public int aplicarEfeito(int vida, Arma arma, Armadura armadura) {
        switch (tipo) {
            case "cura":
                vida += 20;
                System.out.println("Você bebeu a " + nome + " e recuperou 20 de vida!");
                break;
            case "dano":
                arma.setConstanteDano(arma.getConstanteDano() + 5);
                System.out.println("Sua " + arma.getNome() + " ficou mais poderosa! (+5 de dano)");
                break;
            case "defesa":
                armadura.setConstanteDefesa(armadura.getConstanteDefesa() + 3);
                System.out.println("Sua defesa aumentou em +3!");
                break;
        }
        return vida;
    }
    public int aplicarEfeitoInimigo(int vida, int[] danoInimigo) {
        switch (tipo) {
            case "cura":
                vida += 10;
                System.out.println("O inimigo bebeu uma poção e se curou em 10 de vida!");
                break;
            case "dano":
                danoInimigo[0] += 2;
                System.out.println("O inimigo ficou mais forte! (+2 de dano)");
                break;
            case "defesa":
                vida += 5;
                System.out.println("O inimigo endureceu a pele, ganhando +5 de vida temporária!");
                break;
        }
        return vida;
    }
}
