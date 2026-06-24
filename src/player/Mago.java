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
    public void aumentarStatus(Personagem personagem) {
        Scanner scan = new Scanner(System.in);
        int valoresBases;
        if(isSubiuNivel()){
            while(getPontosUp() > 0){
                System.out.println(getNome() + " escolha como quer usar seus pontos: ");
                System.out.println("Cada ponto aumenta +10 ao total");
                System.out.println("1. Vida");
                System.out.println("2. Ataque");
                System.out.println("3. Defesa");
                System.out.println("4. Dano Mágico");
                System.out.println("5. Mana");
                int  op = scan.nextInt();

                switch(op){
                    case 1:{
                        System.out.println("Quantos pontos você quer colocar?");
                        int pontos = scan.nextInt();
                        int pontosTotais = 10 * pontos;
                        valoresBases = getVida();

                        setVida(valoresBases += pontosTotais);
                        setVidaMaxima(getVida());
                    }
                    case 2:{
                        System.out.println("Quantos pontos você quer colocar?");
                        int pontos = scan.nextInt();
                        int pontosTotais = 10 * pontos;
                        valoresBases = getAtaque();

                        setAtaque(valoresBases += pontosTotais);
                    }
                    case 3: {
                        System.out.println("Quantos pontos você quer colocar?");
                        int pontos = scan.nextInt();
                        int pontosTotais = 10 * pontos;

                        valoresBases = getDefesa();

                        setDefesa(valoresBases += pontosTotais);
                    }
                    case 4: {
                        System.out.println("Quantos pontos você quer colocar?");
                        int pontos = scan.nextInt();
                        int pontosTotais = 10 * pontos;

                        this.danoMagico += pontosTotais;
                    }
                    case 5: {
                        System.out.println("Quantos pontos você quer colocar?");
                        int pontos = scan.nextInt();
                        int pontosTotais = 10 * pontos;
                        int manaAtual = getMana();

                        setMana(manaAtual += pontosTotais);
                        setManaMaxima(getMana());
                    }
                }
            }
        }
    }

    public int getDanoMagico() {
        return danoMagico;
    }

    public void setDanoMagico(int danoMagico) {
        this.danoMagico = danoMagico;
    }
}