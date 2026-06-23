package player;

import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Batalha {
    public void batalha(Personagem personagem) {
        LeitorInimigos leitor = new LeitorInimigos();
        List<Inimigo> inimigos = leitor.lerInimigos("inimigos.txt");
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int op;

        boolean jogando = true;
        while(jogando) {
            List<Inimigo> inimigosCompativeis = new ArrayList<>();
            for(Inimigo i : inimigos) {
                if(i.getNivel() <= personagem.getNivel()) {
                    inimigosCompativeis.add(i);
                }
            }
            if(inimigosCompativeis.isEmpty()) {
                inimigosCompativeis = inimigos;
            }

            Inimigo inimigoOriginal = inimigosCompativeis.get(rand.nextInt(inimigosCompativeis.size()));

            Inimigo inimigoAtual = new Inimigo(
                    inimigoOriginal.getNome(),
                    inimigoOriginal.getNivel(),
                    inimigoOriginal.getVida(),
                    inimigoOriginal.getAtaque(),
                    inimigoOriginal.getDefesa(),
                    inimigoOriginal.getXp()
            );

            System.out.println("-------------------------");
            System.out.println("Você está seguindo sua missão... e encontra...");
            System.out.println("Um " + inimigoAtual.getNome() + "!");
            System.out.println("-------------------------");

            while(true) {
                System.out.println("Vida atual: " + personagem.getVida() + " | Vida do inimigo: " + inimigoAtual.getVida());
                System.out.println("O que gostaria de fazer? ");
                System.out.println("1. Ataque físico.");
                System.out.println("2. Defender.");

                if (personagem instanceof Mago || personagem instanceof Paladino) {
                    System.out.println("3. Ataque mágico.");
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
                        if (personagem instanceof Mago) {
                            ((Mago) personagem).atacarMagia(inimigoAtual);

                        } else if (personagem instanceof Paladino) {
                            ((Paladino) personagem).atacarMagia(inimigoAtual);
                        }
                        break;
                    }
                }
                if (inimigoAtual.verificarMorte()) {
                    System.out.println("Você derrotou o inimigo!");
                    personagem.verificarUpNivel(inimigoAtual);
                    break;
                }
                personagem.setDefendendo(false);
                inimigoAtual.aleatorizarAtaques(personagem);

                if(personagem.getVida() <= 0) {
                    System.out.println("Você foi derrotado... Fim de jogo!");
                    jogando = false;
                    break;
                }


                if (!jogando) break;
            }
            System.out.println("Obrigado por jogar!");
        }
    }
}