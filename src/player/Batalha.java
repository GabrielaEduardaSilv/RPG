package player;

import Itens.*;

import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Batalha {
    private int slotAtual = 1;

    public void salvarJogo(Personagem personagem, Scanner scan) {
        System.out.println("=== SALVAR JOGO ===");
        System.out.println("Escolha o slot para salvar (1, 2 ou 3):");
        int slot = scan.nextInt();

        if (slot < 1 || slot > 3) {
            System.out.println("[AVISO] Slot inválido! O jogo só permite salvar até 3 personagens.");
            return;
        }

        String nomeArquivo = "save_slot" + slot + ".dat";

        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(personagem);

            this.slotAtual = slot;

            System.out.println("[SUCESSO] Personagem '" + personagem.getNome() + "' salvo no Slot " + slot + "!");

        } catch (IOException e) {
            System.out.println("[ERRO] Falha ao salvar o progresso.");
        }
    }

    public Personagem carregarJogo(Scanner scan) {
        System.out.println("=======================================");
        System.out.println("          PERSONAGENS SALVOS           ");
        System.out.println("=======================================");

        for (int i = 1; i <= 3; i++) {
            String nomeArquivo = "save_slot" + i + ".dat";
            java.io.File arquivoSave = new java.io.File(nomeArquivo);

            if (arquivoSave.exists()) {
                try (java.io.FileInputStream fis = new java.io.FileInputStream(arquivoSave);
                     java.io.ObjectInputStream ois = new java.io.ObjectInputStream(fis)) {

                    Personagem p = (Personagem) ois.readObject();
                    System.out.println("Slot [" + i + "] -> Nome: " + p.getNome() + " | Nível: " + p.getNivel() + " | Vida: " + p.getVida());

                } catch (Exception e) {
                    System.out.println("Slot [" + i + "] -> [Erro ao ler dados deste slot]");
                }
            } else {
                System.out.println("Slot [" + i + "] -> [VAZIO]");
            }
        }
        System.out.println("=======================================");

        System.out.print("Escolha o slot que deseja carregar (1, 2 ou 3) ou 0 para voltar: ");
        int slot = scan.nextInt();
        scan.nextLine();

        if (slot == 0) {
            return null;
        }

        if (slot < 1 || slot > 3) {
            System.out.println("[AVISO] Slot inválido!");
            return null;
        }

        String nomeArquivoEscolhido = "save_slot" + slot + ".dat";
        java.io.File arquivoEscolha = new java.io.File(nomeArquivoEscolhido);

        if (!arquivoEscolha.exists()) {
            System.out.println("[AVISO] Não há nenhum personagem salvo no Slot " + slot + ".");
            return null;
        }

        try (java.io.FileInputStream fis = new java.io.FileInputStream(arquivoEscolha);
             java.io.ObjectInputStream ois = new java.io.ObjectInputStream(fis)) {

            Personagem personagemCarregado = (Personagem) ois.readObject();
            this.slotAtual = slot;

            System.out.println("[SUCESSO] " + personagemCarregado.getNome() + " carregado com sucesso!");
            batalha(personagemCarregado);
            return personagemCarregado;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERRO] Falha crítica ao carregar o Slot " + slot + ".");
            return null;
        }
    }

    public void deletarSave(int slotUtilizado) {
        if (slotUtilizado < 1 || slotUtilizado > 3) return;

        String nomeArquivo = "save_slot" + slotUtilizado + ".dat";
        File arquivoSave = new File(nomeArquivo);

        if (arquivoSave.exists()) {
            if (arquivoSave.delete()) {
                System.out.println("[PERMADEATH] O personagem faleceu em combate. O save do Slot " + slotUtilizado + " foi completamente apagado!");
            } else {
                System.out.println("[AVISO] Não foi possível deletar o arquivo de save automaticamente.");
            }
        }
    }

    public void batalha(Personagem personagem) {
        LeitorInimigos leitor = new LeitorInimigos();
        List<Inimigo> inimigos = leitor.lerInimigos("src/dados/inimigos");

        LeitorPocoes leitorP = new LeitorPocoes();
        List<Item> bancoDeLoot = new ArrayList<>();
        bancoDeLoot.addAll(leitorP.lerPocoes("src/Itens/pocoes"));

        LeitorEquipamentos leitorE = new LeitorEquipamentos();
        bancoDeLoot.addAll(leitorE.lerEquipamentos("src/Itens/armaduras"));

        LeitorArmas leitorA = new LeitorArmas();
        bancoDeLoot.addAll(leitorA.lerArmas("src/Itens/armas"));

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        boolean chefeDerrotado = false;
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

            if (personagem.getNivel() >= 10 && !chefeDerrotado) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("O chão treme... Você alcançou o ápice da jornada!");
                System.out.println("O CHEFE FINAL APARECEU!");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                inimigoAtual = new Chefe(
                        "SUPER BOSS: " + inimigoAtual.getNome(),
                        10,
                        inimigoAtual.getVida() * 3,
                        inimigoAtual.getAtaque() * 2,
                        inimigoAtual.getDefesa() * 2,
                        inimigoAtual.getXp() * 3
                );
            } else {
                if (rand.nextInt(100) < 25) {
                    System.out.println("[AVISO] Um monstro de ELITE bloqueia seu caminho!");

                    String nomeE = inimigoAtual.getNome() + " Feroz";
                    int lvlE = inimigoAtual.getNivel();
                    int hpE = (int) (inimigoAtual.getVida() * 1.5);
                    int atkE = (int) (inimigoAtual.getAtaque() * 1.3);
                    int defE = inimigoAtual.getDefesa();
                    int xpE = (int) (inimigoAtual.getXp() * 1.5);

                    inimigoAtual = new InimigoElite(nomeE, lvlE, hpE, atkE, defE, xpE);
                }
            }

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
                    case 3: {
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
                    if (personagem.getPontosUp() > 0) {
                        personagem.setSubiuNivel(true);
                    }

                    personagem.aumentarStatus();

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
                            personagem.getInventario().adicionarItem(itemSorteado);
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
                    deletarSave(slotAtual);
                    jogando = false;
                    return;
                }
            }

            Loja mercador = new Loja();
            boolean naParada = true;

            while (naParada) {
                System.out.println("------------------------------------");
                System.out.println(" O que deseja fazer antes de avançar?");
                System.out.println("------------------------------------");
                System.out.println("1. Olhar / Usar itens do Inventário");
                System.out.println("2. Descansar (Recuperar um pouco de Vida/Mana)");
                System.out.println("3. Vender Equipamentos / Comprar Itens(Moedas atuais: " + personagem.getMoedas() + ")");
                System.out.println("4. Continuar a jornada (Próxima Luta)");
                System.out.println("5. Salvar jogo");
                System.out.println("0. Sair");
                System.out.println("------------------------------------");
                System.out.print("Escolha uma opção: ");
                op = scan.nextInt();

                switch(op) {
                    case 1: {
                        System.out.println("--- GERENCIAR EQUIPAMENTOS ---");
                        String armaNome = (personagem.getArmaEquipada() != null) ? personagem.getArmaEquipada().getNome() : "Nenhuma";
                        String armaduraNome = (personagem.getArmaduraEquipada() != null) ? personagem.getArmaduraEquipada().getNome() : "Nenhuma";
                        System.out.println("Arma atual: " + armaNome + " (Ataque total: " + personagem.getAtaque() + ")");
                        System.out.println("Armadura atual: " + armaduraNome + " (Defesa total: " + personagem.getDefesa() + ")");
                        System.out.println("------------------------------");

                        List<Item> equipsDisponiveis = personagem.getInventario().getEquipamentosNoInventario();

                        if (equipsDisponiveis.isEmpty()) {
                            System.out.println("[AVISO] Você não tem armas ou armaduras no inventário!");
                            break;
                        }

                        for (int i = 0; i < equipsDisponiveis.size(); i++) {
                            Item eq = equipsDisponiveis.get(i);
                            System.out.println((i + 1) + ". " + eq.getNome() + " [" + eq.getRaridade() + "]");
                        }
                        System.out.println("0. Voltar");
                        System.out.print("Escolha qual item deseja inspecionar: ");
                        int escolhaEquip = scan.nextInt();

                        if (escolhaEquip == 0 || escolhaEquip > equipsDisponiveis.size()) {
                            System.out.println("Voltando...");
                            break;
                        }

                        Item itemEscolhido = equipsDisponiveis.get(escolhaEquip - 1);

                        if (itemEscolhido instanceof Arma) {
                            ((Arma) itemEscolhido).exibirDetalhes();
                        } else if (itemEscolhido instanceof Equipamento) {
                            ((Equipamento) itemEscolhido).exibirDetalhes();
                        }

                        System.out.println("Deseja equipar este item?");
                        System.out.println("1. Sim, equipar agora");
                        System.out.println("2. Não, voltar ao menu");
                        System.out.print("Escolha: ");
                        int confirmar = scan.nextInt();

                        if (confirmar == 1) {
                            personagem.equiparItem(itemEscolhido);
                        } else {
                            System.out.println("Ação cancelada. O item continua no inventário.");
                        }
                        break;
                    }
                    case 2: {
                        int curaDescanso = (int) (personagem.getVidaMaxima() * 0.2);
                        personagem.setVida(personagem.getVida() + curaDescanso);
                        if (personagem.getVida() > personagem.getVidaMaxima()) {
                            personagem.setVida(personagem.getVidaMaxima());
                        }

                        System.out.println("[DESCANSO] Você sentou ao redor da fogueira. Recuperou " + curaDescanso + " de Vida!");
                        System.out.println("Vida atual: " + personagem.getVida() + "/" + personagem.getVidaMaxima());

                        break;
                    }
                    case 3: {
                        int opcaoLoja;
                        do {
                            System.out.println("-----------------------------------");
                            System.out.println("=== MENU DO MERCADOR ===");
                            System.out.println("1. Ver loja (Comprar)");
                            System.out.println("2. Vender itens");
                            System.out.println("3. Voltar ao Menu Principal");
                            System.out.println("------------------------------------");
                            System.out.print("Escolha uma opção: ");
                            opcaoLoja = scan.nextInt();

                            try {
                                opcaoLoja = scan.nextInt();

                                switch(opcaoLoja) {
                                    case 1: {
                                        System.out.println("--- VITRINE DO MERCADOR ---");
                                        List<Item> itensLoja = mercador.getVitrine();

                                        if (itensLoja.isEmpty()) {
                                            System.out.println("O mercador está sem estoque nesta parada!");
                                            break;
                                        }

                                        for (int i = 0; i < itensLoja.size(); i++) {
                                            Item item = itensLoja.get(i);
                                            System.out.println((i + 1) + ". " + item.getNome() + " | Preço: " + item.getValor() + " moedas");
                                        }

                                        int escolha = scan.nextInt();

                                        if(escolha == 9){
                                            personagem.getInventario().ordenarItensPorPreco();
                                        }

                                        System.out.println("0. Voltar");
                                        System.out.print("Escolha qual item deseja COMPRAR: ");
                                        int escolhaCompra = scan.nextInt();

                                        if (escolhaCompra == 0 || escolhaCompra > itensLoja.size()) {
                                            System.out.println("Voltando ao menu do mercador...");
                                            break;
                                        }

                                        Item itemDesejado = itensLoja.get(escolhaCompra - 1);

                                        if (personagem.getMoedas() < itemDesejado.getValor()) {
                                            System.out.println("[AVISO] Você não tem moedas suficientes!");
                                            break;
                                        }

                                        personagem.setMoedas(personagem.getMoedas() - itemDesejado.getValor());
                                        personagem.getInventario().adicionarItem(itemDesejado);
                                        itensLoja.remove(itemDesejado);

                                        System.out.println("[COMPRA CONCLUÍDA] Você comprou: " + itemDesejado.getNome() + "!");
                                        System.out.println("Saldo restante: " + personagem.getMoedas() + " moedas.");
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("--- MERCADOR: VENDER EQUIPAMENTOS ---");
                                        System.out.println("Suas moedas atuais: " + personagem.getMoedas());
                                        System.out.println("-------------------------------------");

                                        List<Item> equipsDisponiveis = personagem.getInventario().getEquipamentosNoInventario();

                                        if (equipsDisponiveis.isEmpty()) {
                                            System.out.println("[AVISO] Você não tem equipamentos para vender!");
                                            break;
                                        }

                                        for (int i = 0; i < equipsDisponiveis.size(); i++) {
                                            Item eq = equipsDisponiveis.get(i);
                                            System.out.println((i + 1) + ". " + eq.getNome() + " (Preço de venda: " + eq.getValor() + " moedas)");
                                        }
                                        System.out.println("0. Voltar");
                                        System.out.print("Escolha qual item deseja VENDER: ");
                                        int escolhaVenda = scan.nextInt();

                                        if (escolhaVenda == 0 || escolhaVenda > equipsDisponiveis.size()) {
                                            System.out.println("Venda cancelada.");
                                            break;
                                        }

                                        Item itemParaVender = equipsDisponiveis.get(escolhaVenda - 1);

                                        if (itemParaVender == personagem.getArmaEquipada() || itemParaVender == personagem.getArmaduraEquipada()) {
                                            System.out.println("[AVISO] Você não pode vender um item que está EQUIPADO!");
                                            break;
                                        }

                                        int moedasGanhas = itemParaVender.getValor();
                                        personagem.setMoedas(personagem.getMoedas() + moedasGanhas);
                                        personagem.getInventario().removerItem(itemParaVender);

                                        System.out.println("[VENDA CONCLUÍDA] Vendeu por " + moedasGanhas + " moedas!");
                                        break;
                                    }
                                    case 3:
                                        System.out.println("Saindo do mercador...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida!");
                                        break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("[ERRO] Por favor, digite apenas números inteiros!");
                                scan.nextLine();
                                opcaoLoja = 0;
                            }
                        } while(opcaoLoja != 3);

                        break;
                    }
                    case 4: {
                        System.out.println("Você arruma suas coisas e volta a marchar pela estrada...");
                        naParada = false;
                        break;
                    }
                    case 5: {
                        salvarJogo(personagem, scan);
                        break;
                    }
                    case 0:{
                        System.out.println("Saindo para o menu principal...");
                        return;
                    }
                    default:
                        System.out.println("Opção inválida!");
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