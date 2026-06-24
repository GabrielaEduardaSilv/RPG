package Itens;

public class Equipamento extends Item {
    private int defesa;

    public Equipamento(int id, String nome, int defesa, RaridadeItem raridade, int valor) {
        super(id, nome, raridade, valor, TipoItem.ARMADURA);
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

    public void exibirDetalhes() {
        System.out.println("\n===== DETALHES DO EQUIPAMENTO =====");
        System.out.println("Nome: " + getNome());
        System.out.println("Raridade: " + getRaridade());
        System.out.println("Bônus de Defesa: +" + getDefesa());
        System.out.println("Valor de Venda: " + getValor() + " moedas");
        System.out.println("====================================");
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Equipamento outro = (Equipamento) obj;
        return this.defesa == outro.defesa;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + defesa;
        return result;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
}