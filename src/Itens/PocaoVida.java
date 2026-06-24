package Itens;

public class PocaoVida extends Item {

    private int recuperacao;

    public PocaoVida(int id, String nome, int recuperacao, RaridadeItem raridade, int valor) {
        super(id, nome, raridade, valor);
        this.recuperacao = recuperacao;
    }

    @Override
    public String toString() {
        return "Pocao{" +
                "id='" + getId() + '\'' +
                ", nome=" + getNome() +
                ", recuperacao=" + getRecuperacao() +
                ", raridade=" + getRaridade() +
                ", valor=" + getValor() +
                '}';
    }

    public int getRecuperacao() {
        return recuperacao;
    }

    public void setRecuperacao(int recuperacao) {
        this.recuperacao = recuperacao;
    }
}
