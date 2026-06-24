package Itens;

public class Arma extends Item {
    private int dano;
    private int requisitoAtaque = getDano();

    public Arma(int id, String nome, int dano, RaridadeItem raridade, int valor) {
        super(id, nome, raridade, valor, TipoItem.ARMA);
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

    public void exibirDetalhes() {
        System.out.println("\n===== DETALHES DA ARMA =====");
        System.out.println("Nome: " + getNome());
        System.out.println("Raridade: " + getRaridade());
        System.out.println("Bônus de Dano: +" + getDano());
        System.out.println("Requisito de Ataque: " + getRequisitoAtaque());
        System.out.println("Valor de Venda: " + getValor() + " moedas");
        System.out.println("============================");
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // Reutiliza a checagem da classe mãe
        Arma outra = (Arma) obj;
        return this.dano == outra.dano;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + dano;
        return result;
    }

    public int getRequisitoAtaque() {
        return requisitoAtaque;
    }
    public void setRequisitoAtaque(int requisitoAtaque) {
        this.requisitoAtaque = requisitoAtaque;
    }

    public int getDano() {
        return dano;
    }
    public void setDano(int dano) {
        this.dano = dano;
    }
}