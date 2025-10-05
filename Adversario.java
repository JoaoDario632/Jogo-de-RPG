public class Adversario extends Entidade {
        
    public Adversario(String nome, int hp, int forca, int destreza, int agilidade, int constituicao,
                        Arma arma, Armadura armadura, int classeArmadura) {
        super(nome, hp, forca, destreza, agilidade, constituicao, arma, armadura, classeArmadura);
    }




    /*
    1 vampiro (sangramento/regeneração de vida, velocidade 2, 10 hp, defesa 1, CA 14), 2 dmg/acerto + 1 dmg/turno
    2 fortalecimento (hit kill, velocidade 0, 100 hp, defesa 1.5, CA 0), não ataca + hitkill quando seu fortalecimento aumenta seu dano > jogador.vida
    3 gelo (lentidão/congelamento, velocidade 1, 20 hp, defesa 1, CA 13). 1 dmg, reduz velocidade em 10% por ataque até chegar a 0 com 10 ataques

    4 falso divino (divine smite, velocidade 1, 50 hp, defesa 1.2, CA 15), 2 dmg || 10 dmg (1 vez)
    5 adivinho (copia dupla, velocidade 1, 20 hp, defesa 0.9, CA 12). 3 dmg

    6 necromante (revive primeiro boss quando perde metade da vida, revive segundo boss quando fica com 25% de vida,
    ataques impedem regeneração de vida, velocidade 0, 100 hp, defesa 1, CA 14). 5 dmg + 1 dmg/turno
    

    setLoot(loot);
    */
}
