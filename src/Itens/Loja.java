package Itens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Loja {
    private List<Item> vitrine;
    private Random random;

    public Loja() {
        this.vitrine = new ArrayList<>();
        this.random = new Random();
        gerarItensAleatorios();
    }

    public void gerarItensAleatorios() {
        vitrine.clear();

        LeitorArmas leitorArmas = new LeitorArmas();
        LeitorEquipamentos leitorEquips = new LeitorEquipamentos();
        LeitorPocoes leitorPocoes = new LeitorPocoes();

        List<Arma> todasArmas = leitorArmas.lerArmas("armas.txt");
        List<Equipamento> todosEquips = leitorEquips.lerEquipamentos("equipamentos.txt");
        List<Item> todasPocoes = leitorPocoes.lerPocoes("pocoes.txt");

        int quantidadeItens = 3 + random.nextInt(3);

        for (int i = 0; i < quantidadeItens; i++) {
            int categoria = random.nextInt(3);

            switch (categoria) {
                case 0:
                    if (!todasPocoes.isEmpty()) {
                        int index = random.nextInt(todasPocoes.size());
                        vitrine.add(todasPocoes.get(index));
                    }
                    break;

                case 1:
                    if (!todasArmas.isEmpty()) {
                        int index = random.nextInt(todasArmas.size());
                        vitrine.add(todasArmas.get(index));
                    }
                    break;

                case 2:
                    if (!todosEquips.isEmpty()) {
                        int index = random.nextInt(todosEquips.size());
                        vitrine.add(todosEquips.get(index));
                    }
                    break;
            }
        }
    }

    public List<Item> getVitrine() {
        return vitrine;
    }
}