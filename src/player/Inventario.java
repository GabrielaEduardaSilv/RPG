package player;

import Itens.Item;
import Itens.PocaoVida;
import Itens.PocaoMana;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        this.itens.add(item);
        System.out.println("[" + item.getNome() + "] foi guardado no inventário.");
    }

    public void exibirInventario() {
        System.out.println("-------------------------");
        System.out.println("      SEU INVENTÁRIO     ");
        System.out.println("-------------------------");
        if (itens.isEmpty()) {
            System.out.println("Seu inventário está totalmente vazio.");
            return;
        }
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println((i + 1) + ". " + item.getNome() + " [" + item.getRaridade() + "]");
        }
        System.out.println("-------------------------");
    }

    public List<Item> getPocoesNoInventario() {
        List<Item> pocoes = new ArrayList<>();
        for (Item item : itens) {
            if (item instanceof PocaoVida || item instanceof PocaoMana) {
                pocoes.add(item);
            }
        }
        return pocoes;
    }

    public void removerItem(Item item) {
        this.itens.remove(item);
    }

    public List<Item> getItens() {
        return itens;
    }
}