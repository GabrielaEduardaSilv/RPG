package player;

public class Barbaro extends Personagem {
    public Barbaro() {
        super();
        setAtaque(15);
        setDefesa(11);
    }
    @Override
    public void atacar(Personagem alvo) {

        if (alvo.isDefendendo()) {
            getAtaque();
        }

        alvo.setVida(alvo.getVida() - this.getAtaque());
        if (alvo.getVida() < 0) {
            alvo.setVida(0);
        }
        System.out.println("Você causou: " + getAtaque() + " de dano, o inimigo tem: " + alvo.getVida() + " pontos de vida restantes.");
    }
}









