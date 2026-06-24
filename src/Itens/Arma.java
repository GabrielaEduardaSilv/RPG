package Itens;

public class Arma extends Item {
    private int dano;

    public Arma(int id, String nome, int dano, RaridadeItem raridade, int valor) {
        super(id, nome, raridade, valor);
        this.dano = dano;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "id='" + getId() + '\'' +
                ", nome=" + getNome() +
                ", dano=" + getDano() +
                ", raridade=" + getRaridade() +
                ", valor=" + getValor() +
                '}';
    }

    public int getDano() {
        return dano;
    }
    public void setDano(int dano) {
        this.dano = dano;
    }
}
