public class App {
    public static void main(String[] args) throws Exception {
        Menu.exibirMenu();

        Arma arma1 = new Arma("Quebra crânio", 2, "armaPesada");
        Jogador player1 = new Jogador("Pukeman", 50, 4, 2, 1, 3, arma1, null, 10);

        Arma arma2 = new Arma("Foice ceifadora", 2, "armaLeve");
        Adversario adversario1 = new Adversario("Vampiro", 40, 2, 5, 3, 4, arma2, null, 10);

        Combate combate1 = new Combate(player1, adversario1);

        player1.mostrarStatus();
        adversario1.mostrarStatus();
        System.out.println();

        combate1.iniciarCombate(player1, adversario1);
    }
}
