package player;

import Itens.*;

import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Batalha {
    public void batalha(Personagem personagem) {
        LeitorInimigos leitor = new LeitorInimigos();
        List<Inimigo> inimigos = leitor.lerInimigos("src/dados/inimigos");

        LeitorPocoes leitorP = new LeitorPocoes();
        List<Item> bancoDeLoot = new ArrayList<>();
        bancoDeLoot.addAll(leitorP.lerPocoes("src/Itens/Pocoes"));

        LeitorEquipamentos leitorE = new LeitorEquipamentos();
        bancoDeLoot.addAll(leitorE.lerEquipamentos("src/Itens/Armaduras"));

        LeitorArmas leitorA = new LeitorArmas();
        bancoDeLoot.addAll(leitorA.lerArmas("src/Itens/Armas"));

        Inventario inventario = new Inventario();

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int op;

        boolean jogando = true;
        int mortesTotais = 0;

        while (jogando) {
            List<Inimigo> inimigosCompativeis = new ArrayList<>();
            for (Inimigo i : inimigos) {
                if (i.getNivel() <= personagem.getNivel()) {
                    inimigosCompativeis.add(i);
                }
            }
            if (inimigosCompativeis.isEmpty()) {
                inimigosCompativeis = inimigos;
            }

            Inimigo inimigoOriginal = inimigosCompativeis.get(rand.nextInt(inimigosCompativeis.size()));
            Inimigo inimigoAtual = new Inimigo(inimigoOriginal.getNome(), inimigoOriginal.getNivel(), inimigoOriginal.getVida(), inimigoOriginal.getAtaque(), inimigoOriginal.getDefesa(), inimigoOriginal.getXp());

            System.out.println("-------------------------");
            System.out.println("Você está seguindo sua missão... e encontra...");
            System.out.println("Um " + inimigoAtual.getNome() + "!");
            System.out.println("-------------------------");

            while (true) {
                System.out.println("Vida atual: " + personagem.getVida() + " | Vida do inimigo: " + inimigoAtual.getVida());
                System.out.println("O que gostaria de fazer? ");
                System.out.println("1. Ataque físico");
                System.out.println("2. Defender");
                System.out.println("3. Usar poção");

                if (personagem instanceof Mago || personagem instanceof Paladino) {
                    System.out.println("4. Ataque mágico.");
                }

                op = scan.nextInt();

                switch (op) {
                    case 1: {
                        inimigoAtual.setDefendendo(false);
                        personagem.atacar(inimigoAtual);
                        break;
                    }
                    case 2: {
                        personagem.setDefendendo(true);
                        System.out.println("Você se preparou para defender!");
                        break;
                    }
                    case 3:{
                        List<Item> pocoesDisponiveis = personagem.getInventario().getPocoesNoInventario();

                        if (pocoesDisponiveis.isEmpty()) {
                            System.out.println("[AVISO] Você não tem nenhuma poção no seu inventário!");
                            continue;
                        }

                        System.out.println("--- SUAS POÇÕES ---");
                        for (int i = 0; i < pocoesDisponiveis.size(); i++) {
                            Item p = pocoesDisponiveis.get(i);
                            String tipo = (p instanceof PocaoVida) ? "Vida" : "Mana";
                            System.out.println((i + 1) + ". " + p.getNome() + " [Foco: " + tipo + "]");
                        }
                        System.out.println("0. Voltar");
                        System.out.print("Escolha qual usar: ");

                        int escolhaPocao = scan.nextInt();

                        if (escolhaPocao == 0 || escolhaPocao > pocoesDisponiveis.size()) {
                            System.out.println("Ação cancelada.");
                            continue;
                        }

                        Item pocaoEscolhida = pocoesDisponiveis.get(escolhaPocao - 1);

                        personagem.usarPocao(pocaoEscolhida);
                        break;
                    }
                    case 4: {
                        inimigoAtual.setDefendendo(false);
                        if (personagem instanceof Mago) {
                            ((Mago) personagem).atacarMagia(inimigoAtual);
                        } else if (personagem instanceof Paladino) {
                            ((Paladino) personagem).atacarMagia(inimigoAtual);
                        }
                        break;
                    }
                }
                if (inimigoAtual.verificarMorte()) {
                    mortesTotais++;
                    System.out.println("Você derrotou o inimigo!");
                    System.out.println("Até agora sua contagem de mortes é: " + mortesTotais);
                    personagem.verificarUpNivel(inimigoAtual);
                    personagem.aumentarStatus(personagem);

                    int chance = rand.nextInt(100);
                    if (chance < 30) {
                        System.out.println("[SORTE!] O inimigo deixou cair um baú de espólios!");

                        if (!bancoDeLoot.isEmpty()) {
                            int dadoRaridade = rand.nextInt(100);
                            RaridadeItem raridadeSorteada;

                            if (dadoRaridade < 50) {
                                raridadeSorteada = RaridadeItem.COMUM;
                            } else if (dadoRaridade < 80) {
                                raridadeSorteada = RaridadeItem.RARO;
                            } else if (dadoRaridade < 93) {
                                raridadeSorteada = RaridadeItem.EPICO;
                            } else if (dadoRaridade < 99) {
                                raridadeSorteada = RaridadeItem.LENDARIO;
                            } else {
                                raridadeSorteada = RaridadeItem.UNICO;
                            }

                            List<Item> itensFiltrados = new ArrayList<>();
                            for (Item i : bancoDeLoot) {
                                if (i.getRaridade() == raridadeSorteada) {
                                    itensFiltrados.add(i);
                                }
                            }

                            if (itensFiltrados.isEmpty()) {
                                itensFiltrados = bancoDeLoot;
                            }

                            int indiceSorteado = rand.nextInt(itensFiltrados.size());
                            Item itemSorteado = itensFiltrados.get(indiceSorteado);

                            System.out.println("[BAÚ] Você abriu e encontrou um item [" + raridadeSorteada + "]: " + itemSorteado.getNome() + "!");
                            inventario.adicionarItem(itemSorteado);
                        }
                    } else {
                        System.out.println("O inimigo não deixou cair nenhum item desta vez.");
                    }

                    break;
                }

                System.out.println();

                personagem.setDefendendo(false);
                inimigoAtual.aleatorizarAtaques(personagem);

                if (personagem.getVida() <= 0) {
                    System.out.println("Você foi derrotado... Fim de jogo.");
                    jogando = false;
                    break;
                }
            }

            if (!jogando) {
                break;
            }
        }
        System.out.println("Você matou: " + mortesTotais + " inimigos!");
        System.out.println("Obrigado por jogar!");
    }
}