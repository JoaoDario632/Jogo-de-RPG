public class Jogador extends Entidade{
    private int nivel;

    public Jogador(String nome, int hp, int forca, int destreza, int agilidade, int constituicao,
                    Arma arma, Armadura armadura, int classeArmadura) {
        super(nome, hp, forca, destreza, agilidade, constituicao, arma, armadura, classeArmadura);
        this.nivel = 1;
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
