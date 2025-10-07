import java.util.Random;

public class Combate {
    private Jogador jogador;
    private Adversario adversario;
    private Random random = new Random();

    public Combate(Jogador jogador, Adversario adversario) {
        setJogador(jogador);
        setAdversario(adversario);
    }
    
    public void atacar(Entidade atacante, Entidade atacado, Arma arma){
        switch (arma.getCategoria()) {
            case "armaPesada":
                double valorD20Forca = atacante.rolarDados(20);
                double ataqueForca = valorD20Forca + atacante.getForca() + 2;
                if(valorD20Forca == 20){
                    critar(atacante, atacado, arma);
                } else if (atacado.getClasseArmadura() < ataqueForca && valorD20Forca != 20) {
                    System.out.println("Resultado " + ataqueForca + " (D20: " + atacante.getRolagem() + " + For: " + atacante.getForca() + 
                    " + K: 2)! " + atacante.getNome() + " foi bem sucedido no ataque contra o CA " + atacado.getClasseArmadura() + " de "
                    + atacado.getNome());
                    infligirDano(atacante, atacado, arma);
                } else System.out.println("Resultado " + ataqueForca + " (D20: " + atacante.getRolagem() + " + For: " + atacante.getForca() +
                    " + K: 2)! " + atacante.getNome() + " Errou\n");
                break;
                
            case "armaLeve":
            int valorD20Destreza = atacante.rolarDados(20);
            int ataqueDestreza = valorD20Destreza + atacante.getDestreza() + 2;
                if(valorD20Destreza == 20){
                    critar(atacante, atacado, arma);
                } else if (atacado.getClasseArmadura() < (valorD20Destreza + atacante.getDestreza())+2) {
                    System.out.println("Resultado " + ataqueDestreza + " (D20: " + atacante.getRolagem() + " + Dex: " + atacante.getDestreza()
                    + " + K: 2)! " + atacante.getNome() + " foi bem sucedido no ataque contra o CA " + atacado.getClasseArmadura() + " de "
                    + atacado.getNome());
                    infligirDano(atacante, atacado, arma);
                }else System.out.println("Resultado " + ataqueDestreza + " (D20: " + atacante.getRolagem() + " + Dex: " + atacante.getDestreza() +
                    " + K: 2)! " + atacante.getNome() + " Errou\n");
                break;

            default:
                System.out.println("Categoria de arma não encontrada. Não será possível " + atacante.getNome() +
                                    " atacar.\nBoa sorte!");
                break;
        }
    }
    
    public void critar(Entidade infligidor, Entidade receptor, Arma arma){
        switch (arma.getCategoria()) {
            case "armaPesada":
                double danoForca = arma.getConstanteDano() + infligidor.getForca()*1.5 + infligidor.rolarDados(12);
                System.out.println("RESULTADO 20!!! ACERTO CRÍTICO!!!\nO DANO TOTAL É DOBRADO!!!\n" +
                infligidor.getNome() + " causou " + danoForca*2 + " de dano em " + receptor.getNome() + "!!!");
                receptor.setHp(receptor.getHp() - danoForca*2);
                break;
            
            case "armaLeve":
                double danoDestreza = arma.getConstanteDano() + infligidor.getDestreza() + infligidor.rolarDados(6) +
                infligidor.rolarDados(6) + infligidor.rolarDados(4);
                System.out.println("RESULTADO 20!!! ACERTO CRÍTICO!!!\n O DANO TOTAL É DOBRADO!!!\n" +
                infligidor.getNome() + " causou " + danoDestreza*2 + " de dano em " + receptor.getNome() + "!!!");
                receptor.setHp(receptor.getHp() - danoDestreza*2);
                break;
                    
            default:
                System.out.println("Categoria de arma não encontrada. Não foi possível " + infligidor.getNome() +
                " infligir dano.\nBoa sorte! KSJSKSJSDJKKJS");
                break;
        }

        if(receptor.getHp() < 0) receptor.setHp(0);     
        infligidor.limparHistoricoRolagens();
        infligidor.mostrarStatus();
        receptor.mostrarStatus();
        System.out.println();
    }

    public void infligirDano(Entidade infligidor, Entidade receptor, Arma arma){
        switch (arma.getCategoria()) {
            case "armaPesada":
                double danoForca = arma.getConstanteDano() + infligidor.getForca()*1.5 + infligidor.rolarDados(12);
                receptor.setHp(receptor.getHp() - danoForca);
                System.out.println(infligidor.getNome() + " ataca " + receptor.getNome() + " com " + arma.getNome() + " causando " +
                danoForca + " de dano (D12: " + infligidor.getRolagem() + " + For: " + infligidor.getForca()*1.5 + " + " + " + K: " +
                arma.getConstanteDano() + ")!");
                break;
            
            case "armaLeve":
                double danoDestreza = arma.getConstanteDano() + infligidor.getDestreza() + infligidor.rolarDados(6) +
                infligidor.rolarDados(6) + infligidor.rolarDados(4);
                receptor.setHp(receptor.getHp() - danoDestreza);
                System.out.println(infligidor.getNome() + " ataca " + receptor.getNome() + " com " + arma.getNome() + " causando " +
                danoDestreza + " de dano (D6: " + infligidor.getRolagem() + " + D6: " + infligidor.getRolagem() + " + D4: " +
                                                    infligidor.getRolagem() + " + Dex: " + infligidor.getDestreza() + " + K: " +
                                                    arma.getConstanteDano() + ")!");
                break;

            default:
                System.out.println("Categoria de arma não encontrada. Não foi possível " + infligidor.getNome() +
                " infligir dano.\nBoa sorte!");
                break;
        }

        if(receptor.getHp() < 0) receptor.setHp(0);
        infligidor.mostrarStatus();
        receptor.mostrarStatus();
        System.out.println();
    }

    public void iniciarCombate(Entidade combatente1, Entidade combatente2){
        int aleatorio = random.nextInt(2);
        if(combatente1.getAgilidade() > combatente2.getAgilidade()){
            do{
                if (combatente1.getHp() > 0) atacar(combatente1, combatente2, combatente1.getArma());
                else break;

                if (combatente2.getHp() > 0) atacar(combatente2, combatente1, combatente2.getArma());
                else break;
            } while(combatente1.getHp() > 0 && combatente2.getHp() > 0);

        }else if(combatente2.getAgilidade() > combatente1.getAgilidade()){
            do{
                if (combatente2.getHp() > 0) atacar(combatente2, combatente1, combatente2.getArma());
                else break;

                if (combatente1.getHp() > 0) atacar(combatente1, combatente2, combatente1.getArma());
                else break;
            } while(combatente1.getHp() > 0 && combatente2.getHp() > 0);

        }else{
            switch (aleatorio) {
                case 0:
                    do{
                        if (combatente1.getHp() > 0) atacar(combatente1, combatente2, combatente1.getArma());
                        else break;

                        if (combatente2.getHp() > 0) atacar(combatente2, combatente1, combatente2.getArma());
                        else break;
                    } while(combatente1.getHp() > 0 && combatente2.getHp() > 0);
                    break;
            
                case 1:
                    do{
                        if (combatente2.getHp() > 0) atacar(combatente2, combatente1, combatente2.getArma());
                        else break;

                        if (combatente1.getHp() > 0) atacar(combatente1, combatente2, combatente1.getArma());
                        else break;
                    } while(combatente1.getHp() > 0 && combatente2.getHp() > 0);

                default:
                    break;
            }
        }
    }

    public Jogador getJogador() {
        return jogador;
    }
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Adversario getAdversario() {
        return adversario;
    }
    public void setAdversario(Adversario adversario) {
        this.adversario = adversario;
    }
}