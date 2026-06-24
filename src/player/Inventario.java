package player;

import Itens.Item;
import Itens.PocaoMana;
import Itens.PocaoVida;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Inventario implements java.io.Serializable{
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

    public List<Item> getEquipamentosNoInventario() {
        List<Item> equipamentos = new ArrayList<>();
        for (Item item : itens) {
            if (!(item instanceof PocaoVida) && !(item instanceof PocaoMana)) {
                equipamentos.add(item);
            }
        }
        return equipamentos;
    }

    public void ordenarItensPorPreco() {
        if (itens == null || itens.isEmpty()) {
            System.out.println("[AVISO] O inventário está vazio. Nada para ordenar!");
            return;
        }
        Collections.sort(itens, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return Integer.compare(item1.getValor(), item2.getValor());
            }
        });

        System.out.println("[SUCESSO] Inventário ordenado por preço com sucesso!");
    }

    public void removerItem(Item item) {
        this.itens.remove(item);
    }

    public List<Item> getItens() {
        return itens;
    }
}