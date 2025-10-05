public abstract class Equipamento {
    private String nome;
    private boolean equipado;
    
    public Equipamento(String nome) {
        setNome(nome);
        equipado = false;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEquipado() {
        return equipado;
    }
    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }
}
