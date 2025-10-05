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
                    Jogo jogo = new Jogo(sc);
                    jogo.comecar();
                    break;
                case 2:
                    Historia.mostrar(sc);
                    break;
                case 3:
                    System.out.println("Saindo do jogo... Até a próxima!");
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    } 
}