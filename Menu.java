import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    
    public static void exibirMenu() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("=========================");
            System.out.println("  JRPG - PUKE ADVENTURE  ");
            System.out.println("=========================");
            System.out.println("1. Começar Jogo");
            System.out.println("2. História");
            System.out.println("3. Sair");

            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    Arma armaEscolhida = escolherArma(); 
                    Jogo jogo = new Jogo(sc, armaEscolhida);
                    jogo.comecar();
                    break;
                case 2:
                    mostrarHistoria();
                    break;
                case 3:
                    System.out.println("Saindo do jogo... Até a próxima!");
                    rodando = false;
                    sc.close();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void mostrarHistoria() {
        System.out.println("\n=== História ===");
        System.out.println("Em um mundo distante, onde a magia e as espadas\n" +
                           "moldam o destino das pessoas, um herói se ergue.\n" +
                           "Movido pela coragem e pela necessidade de proteger\n" +
                           "sua terra natal, ele deve enfrentar inimigos poderosos.\n" +
                           "Somente superando os desafios poderá restaurar a paz.");
        System.out.println("\nPressione ENTER para voltar ao menu.");
        sc.nextLine();
    }

    private static Arma escolherArma() {
        Arma[] armas = {
            new Arma("Espada Longa", 15, "Espada"),
            new Arma("Machado de Batalha", 20, "Machado"),
            new Arma("Arco e Flecha", 10, "Arco")
        };

        System.out.println("\n=== Escolha sua arma ===");
        for (int i = 0; i < armas.length; i++) {
            System.out.println((i + 1) + ". " + armas[i].getNome() + " (Dano: " + armas[i].getConstanteDano() + ")");
        }
        System.out.print("Digite o número da arma desejada: ");

        int escolha = sc.nextInt();
        sc.nextLine();
        if (escolha >= 1 && escolha <= armas.length) {
            System.out.println("Você escolheu: " + armas[escolha - 1].getNome());
            return armas[escolha - 1];
        } else {
            System.out.println("Escolha inválida. Será atribuída a primeira arma.");
            return armas[0];
        }
    }
}
