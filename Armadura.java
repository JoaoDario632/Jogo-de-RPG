public class Armadura extends Equipamento{
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
}
