package player;

import java.util.Random;

public class Inimigo extends Personagem {
    private int danoTotal;

    public Inimigo(String nome, int nivel, int vida, int ataque, int defesa, int xp) {
        super(nome, nivel, vida, ataque, defesa, xp);
    }

    public Inimigo() {}

    @Override
    public String toString() {
        return "Inimigo{" +
                "nome='" + getNome() + '\'' +
                ", vida=" + getVida() +
                ", ataque=" + getAtaque() +
                ", defesa=" + getDefesa() +
                ", nivel=" + getNivel() +
                ", xp=" + getXp() +
                '}';
    }

    @Override
    public void atacar(Personagem alvo) {
        Random rand = new Random();
        int danoAleatorio = rand.nextInt(getAtaque())+1;
        this.danoTotal = danoAleatorio;
        if(this.danoTotal > alvo.getVida()){
            this.danoTotal = alvo.getVida();
        }
        alvo.setVida(alvo.getVida() - this.danoTotal);
    }

    @Override
    public void aumentarStatus(Personagem personagem) {

    }

    public void aleatorizarAtaques(Personagem alvo) {
        this.setDefendendo(false);
        Random rand = new Random();
        int escolha = rand.nextInt(2) + 1;

        switch(escolha) {
            case 1: {
                if(alvo.isDefendendo()) {
                    if(alvo.getDefesa() >= 5 && alvo.getDefesa() <= 10) {
                        danoTotal = getAtaque() - 5;
                    } else if (alvo.getDefesa() >= 11 && alvo.getDefesa() <= 15) {
                        danoTotal = getAtaque() - 10;
                    } else {
                        danoTotal = getAtaque() - 20;
                    }
                    if (danoTotal < 0) danoTotal = 0;

                    if(this.danoTotal > alvo.getVida()){
                        this.danoTotal = alvo.getVida();
                    }

                    alvo.setVida(alvo.getVida() - danoTotal);

                } else {
                    atacar(alvo);
                }

                System.out.println("O inimigo causou: " + getDanoTotal() + " de dano.");

                break;
            }
            case 2: {
                this.setDefendendo(true);
                System.out.println("O inimigo escolheu se defender!");
                break;
            }
        }
    }

    public boolean verificarMorte() {
        return getVida() <= 0;
    }

    public int getDanoTotal() {
        return danoTotal;
    }

    public void setDanoTotal(int danoTotal) {
        this.danoTotal = danoTotal;
    }
}