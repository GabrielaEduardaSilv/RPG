package player;

public class Barbaro extends Personagem {
    public Barbaro() {
        super();
        setAtaque(15);
        setDefesa(11);
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
}