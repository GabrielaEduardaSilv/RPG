package player;

import java.util.Random;

public class Inimigo extends Personagem {
    public Inimigo(String nome, int nivel, int vida, int ataque, int defesa, int xp) {
        super(nome, nivel, vida, ataque, defesa, xp);
    }

    public Inimigo() {
    }

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
        alvo.setVida(alvo.getVida() - getAtaque());
    }

    public void aleatorizarAtaques(Personagem alvo) {
        this.setDefendendo(false);
        Random rand = new Random();

        int escolha = rand.nextInt(2) + 1;

        switch (escolha) {
            case 1: {
                atacar(alvo);
                break;
            }
            case 2: {
                this.setDefendendo(true);
                System.out.println(getNome() + " Escolheu se defender!");
                break;
            }
        }
    }

    public boolean verificarMorte() {
        if (getVida() <= 0) {
            return true;
        }
        return false;
    }
}