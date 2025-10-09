public class Jogador extends Entidade {
    private int nivel;
    private int experiencia;
    private int XpEssencial;

    public Jogador(String nome, int hp, int forca, int destreza, int agilidade, int constituicao,
                   Arma arma, Armadura armadura, int classeArmadura) {
        super(nome, hp, forca, destreza, agilidade, constituicao, arma, armadura, classeArmadura);
        this.nivel = 1;
        this.experiencia = 0;
        this.XpEssencial = 100; 
    }
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getExperienciaNecessaria() {
        return XpEssencial;
    }

    public void setExperienciaNecessaria(int XpEssencial) {
        this.XpEssencial = XpEssencial;
    }

    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        System.out.println(getNome() + " ganhou " + xp + " de experiência!");
        verificarSubirNivel();
    }

    private void verificarSubirNivel() {
        while (experiencia >= XpEssencial) {
            experiencia -= XpEssencial;
            nivel++;
            XpEssencial *= 1.5; // aumenta a dificuldade gradualmente
            aumentarAtributos();
            System.out.println("parabéns! " + getNome() + " subiu para o nível " + nivel + "!");
        }
    }

    private void aumentarAtributos() {
        setHp(getHp() + 10);
        setForca(getForca() + 2);
        setDestreza(getDestreza() + 1);
        setConstituicao(getConstituicao() + 1);
        setAgilidade(getAgilidade() + 1);
        System.out.println("Atributos aprimorados com o novo nível!");
    }


    public void equiparArma(Arma novaArma) {
        if (novaArma != null) {
            System.out.println(getNome() + " trocou sua arma para " + novaArma.getNome() + "!");
            setArma(novaArma);
        } else {
            System.out.println("Nenhuma arma selecionada.");
        }
    }

    public void equiparArmadura(Armadura novaArmadura) {
        if (novaArmadura != null) {
            System.out.println(getNome() + " equipou " + novaArmadura.getNome() + "!");
            setArmadura(novaArmadura);
        } else {
            System.out.println("Nenhuma armadura selecionada.");
        }
    }

   public int atacar() {
    int danoBase = (int) (getForca() + (getArma() != null ? getArma().getConstanteDano() : 0));
    System.out.println(getNome() + " ataca causando " + danoBase + " de dano!");
    return danoBase;
    }

    @Override
    public String toString() {
        return String.format(
            """
            Jogador: %s (Nível %d)
            HP: %d | Força: %d | Destreza: %d | Agilidade: %d | Constituição: %d
            Arma: %s | Armadura: %s
            XP: %d / %d
            """,
            getNome(), nivel, getHp(), getForca(), getDestreza(), getAgilidade(), getConstituicao(),
            (getArma() != null ? getArma().getNome() : "Nenhuma"),
            (getArmadura() != null ? getArmadura().getNome() : "Nenhuma"),
            experiencia, XpEssencial
        );
    }
}
