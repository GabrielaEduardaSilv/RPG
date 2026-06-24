package player;

import java.util.Scanner;

public class Mago extends Personagem {
    private int danoMagico = 15;

    public Mago() {
        super();
        setAtaque(5);
        setDefesa(5);
        setMana(20);
        setManaMaxima(20);
    }

    public void atacarMagia(Personagem alvo) {
        alvo.setVida(alvo.getVida() - danoMagico);
        int custoMana = 5;
        setMana(getMana() - custoMana);
        if (alvo.getVida() < 0) {
            alvo.setVida(0);
        }
        System.out.println("Você causou: " + danoMagico + " de dano mágico, o inimigo tem: " + alvo.getVida() + " pontos de vida restantes.");
        System.out.println("Mana restante: " + getMana() + "/" + getManaMaxima());
    }

    @Override
    public void atacar(Personagem alvo) {
        int danoTotal = 0;
        if (alvo.isDefendendo()) {
            if (alvo.getDefesa() >= 5 && alvo.getDefesa() <= 10) {
                danoTotal = getAtaque() - 5;
            } else if (alvo.getDefesa() >= 11 && alvo.getDefesa() <= 15) {
                danoTotal = getAtaque() - 10;
            } else {
                danoTotal = getAtaque() - 20;
            }
            if (danoTotal < 0) danoTotal = 0;
        } else {
            danoTotal = getAtaque();

        }
        alvo.setVida(alvo.getVida() - danoTotal);
        System.out.println("Você causou: " + getAtaque() + " de dano, o inimigo tem: " + alvo.getVida() + " pontos de vida restantes.");
    }

    @Override
    public void aumentarStatus() {
        Scanner scan = new Scanner(System.in);
        int valoresBases;

        if (isSubiuNivel()) {
            while (getPontosUp() > 0) {
                System.out.println("-------------------------------------");
                System.out.println(getNome() + ", você tem " + getPontosUp() + " pontos para distribuir!");
                System.out.println("Cada ponto colocado aumenta +10 no status.");
                System.out.println("-------------------------------------");
                System.out.println("1. Vida");
                System.out.println("2. Ataque");
                System.out.println("3. Defesa");
                System.out.println("4. Dano Mágico");
                System.out.println("5. Mana");
                System.out.print("Escolha uma opção: ");
                int op = scan.nextInt();

                System.out.print("Quantos pontos você quer colocar? ");
                int pontos = scan.nextInt();

                if (pontos > getPontosUp() || pontos <= 0) {
                    System.out.println("[AVISO] Quantidade de pontos inválida! Tente novamente.");
                    continue;
                }

                int pontosTotais = 10 * pontos;

                switch (op) {
                    case 1: {
                        valoresBases = getVidaMaxima();
                        setVidaMaxima(valoresBases + pontosTotais);
                        setVida(getVidaMaxima());
                        System.out.println("Vida Máxima aumentada para: " + getVidaMaxima());
                        break;
                    }
                    case 2: {
                        valoresBases = getAtaque();
                        setAtaque(valoresBases + pontosTotais);
                        System.out.println("Ataque aumentado para: " + getAtaque());
                        break;
                    }
                    case 3: {
                        valoresBases = getDefesa();
                        setDefesa(valoresBases + pontosTotais);
                        System.out.println("Defesa aumentada para: " + getDefesa());
                        break;
                    }
                    case 4: {
                        this.danoMagico += pontosTotais;
                        System.out.println("Dano Mágico aumentado para: " + this.danoMagico);
                        break;
                    }
                    case 5: {
                        int manaMaximaAtual = getManaMaxima();
                        setManaMaxima(manaMaximaAtual + pontosTotais);
                        setMana(getManaMaxima());
                        System.out.println("Mana Máxima aumentada para: " + getManaMaxima());
                        break;
                    }
                    default:
                        System.out.println("Opção de status inválida!");
                }

                setPontosUp(getPontosUp() - pontos);
            }

            setSubiuNivel(false);
            System.out.println("[SUCESSO] Todos os pontos foram distribuídos!");
        }
    }

    public int getDanoMagico() {
        return danoMagico;
    }

    public void setDanoMagico(int danoMagico) {
        this.danoMagico = danoMagico;
    }
}