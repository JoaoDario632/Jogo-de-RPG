import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Armadura extends Equipamento {
    private int constanteDefesa;

    public Armadura(String nome, int constanteDefesa) {
        super(nome);
        setConstanteDefesa(constanteDefesa);
    }

    public int getConstanteDefesa() {
        return constanteDefesa;
    }

    public void setConstanteDefesa(int constanteDefesa) {
        this.constanteDefesa = constanteDefesa;
    }

    // ======= Sistema de escolha de armadura =======
    public static Armadura escolherArmadura(Scanner sc) {
        List<Armadura> armaduras = new ArrayList<>();
        armaduras.add(new Armadura("Armadura de Couro", 5));
        armaduras.add(new Armadura("Armadura de Ferro", 10));
        armaduras.add(new Armadura("Armadura de Aço", 15));
        armaduras.add(new Armadura("Armadura Lendária", 25));

        System.out.println("\n=== Escolha sua armadura ===");
        for (int i = 0; i < armaduras.size(); i++) {
            Armadura armadura = armaduras.get(i);
            System.out.println((i + 1) + ". " + armadura.getNome() + " - Defesa: " + armadura.getConstanteDefesa());
        }

        System.out.print("Digite o número da armadura que deseja escolher: ");
        int escolha = sc.nextInt();
        sc.nextLine(); // limpar buffer

        if (escolha >= 1 && escolha <= armaduras.size()) {
            Armadura armaduraEscolhida = armaduras.get(escolha - 1);
            System.out.println("Você escolheu: " + armaduraEscolhida.getNome() + "!\n");
            return armaduraEscolhida;
        } else {
            System.out.println("Opção inválida. Nenhuma armadura foi escolhida.\n");
            return null;
        }
    }
}
