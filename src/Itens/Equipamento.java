package Itens;

public class Equipamento extends Item {
    private int defesa;

    public Equipamento(int id, String nome, int recuperacao, RaridadeItem raridade, int valor) {
        super(id, nome, raridade, valor);
        this.defesa = defesa;
    }

    @Override
    public String toString() {
            return "Equipamento{" +
                "id='" + getId() + '\'' +
                ", nome=" + getNome() +
                ", defesa=" + getDefesa() +
                ", raridade=" + getRaridade() +
                ", valor=" + getValor() +
                '}';
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
}
