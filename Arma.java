    public class Arma extends Equipamento{
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
    }
