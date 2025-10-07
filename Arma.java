import java.util.Scanner;

public class Arma extends Equipamento {
    private int constanteDano;
    private String categoria;

    public Arma(String nome, int constanteDano, String categoria) {
        super(nome);
        setConstanteDano(constanteDano);
        setCategoria(categoria);
    }

    public int getConstanteDano() {
        return constanteDano;
    }

    public void setConstanteDano(int constanteDano) {
        this.constanteDano = constanteDano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // ✅ Método que o Jogo.java usa ao subir de nível
    public void aumentarDano(int incremento) {
        if (incremento > 0) {
            this.constanteDano += incremento;
            System.out.println("Sua arma foi aprimorada! Dano aumentado em +" + incremento);
        }
    }

    public static Arma escolherArma(Scanner sc) {
        System.out.println("\n=== Escolha sua Arma ===");
        System.out.println("1. Espada (Dano 10, Categoria: Corpo a Corpo)");
        System.out.println("2. Machado (Dano 12, Categoria: Corpo a Corpo)");
        System.out.println("3. Arco (Dano 8, Categoria: Distância)");
        System.out.print("Digite sua escolha: ");

        int escolha = sc.nextInt();
        sc.nextLine();

        switch (escolha) {
            case 1:
                return new Arma("Espada", 10, "Corpo a Corpo");
            case 2:
                return new Arma("Machado", 12, "Corpo a Corpo");
            case 3:
                return new Arma("Arco", 8, "Distância");
            default:
                System.out.println("Opção inválida. Você ficará sem arma!");
                return null;
        }
    }

    @Override
    public String toString() {
        return getNome() + " (Dano: " + constanteDano + ", Categoria: " + categoria + ")";
    }
}
