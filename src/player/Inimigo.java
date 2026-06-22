package player;

import java.util.Random;

public class Inimigo extends Personagem{
    public Inimigo(String nome, int nivel, int vida, int ataque, int defesa, int xp){
        super(nome, nivel, vida, ataque, defesa, xp);
    }

    public Inimigo(){}

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
    public void atacar(Personagem alvo){
        alvo.setVida(alvo.getVida() - getAtaque());
    }

    public void aleatorizarAtaques(){
        Random rand = new Random();

    }

    public boolean verificarMorte(){
        if(getVida() <= 0){
            return true;
        }
        return false;
    }
}