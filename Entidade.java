import java.util.ArrayList;
import java.util.Random;

public abstract class Entidade {
    private String nome;
    private double hp;
    private double forca;
    private int destreza;
    private int agilidade;
    private int constituicao;
    private Arma arma;
    private Armadura armadura;
    private int classeArmadura;
    private Random random = new Random();
    private ArrayList<Integer> historicoRolagens = new ArrayList<>();
    
    public Entidade(String nome, double hp, double forca, int destreza, int agilidade,
                    int constituicao, Arma arma, Armadura armadura, int classeArmadura) {
        setNome(nome);
        setHp(hp);
        setForca(forca);
        setDestreza(destreza);
        setAgilidade(agilidade);
        setConstituicao(constituicao);
        setArma(arma);
        setArmadura(armadura);
        setClasseArmadura(classeArmadura);
    }

    public void mostrarStatus(){
        System.out.println(this.nome + ":       \t" + hp + " hp");
    }
    
    public int rolarDados(int faces){
        int rolagem = random.nextInt(1, faces) + 1;
        historicoRolagens.add(rolagem);
        return rolagem;
    }
    public int getRolagem(){
        int valorRolagem = historicoRolagens.get(0);
        historicoRolagens.remove(0);
        return valorRolagem;
    }
    public void limparHistoricoRolagens(){
        historicoRolagens.clear();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getHp() {
        return hp;
    }
    public void setHp(double hp) {
        if (hp >= 0) this.hp = hp;
        else this.hp = 0;
    }

    public double getForca() {
        return forca;
    }
    public void setForca(double forca) {
        this.forca = forca;
    }

    public int getDestreza() {
        return destreza;
    }
    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getAgilidade() {
        return agilidade;
    }
    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public int getConstituicao() {
        return constituicao;
    }
    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public Arma getArma() {
        return arma;
    }
    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Armadura getArmadura() {
        return armadura;
    }
    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }

    public int getClasseArmadura() {
        return classeArmadura;
    }
    public void setClasseArmadura(int classeArmadura) {
        this.classeArmadura = classeArmadura;
    }
}
