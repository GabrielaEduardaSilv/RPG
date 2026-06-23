package player;

public class Mago extends Personagem {
    private int mana = 20;
    private int danoMagico = 15;

    public Mago() {
        super();
        setAtaque(5);
        setDefesa(5);
    }

    public void atacarMagia(Personagem alvo) {
        alvo.setVida(alvo.getVida() - danoMagico);
        if (alvo.getVida() < 0) {
            alvo.setVida(0);
        }
        System.out.println("Você causou: " + danoMagico + " de dano mágico, o inimigo tem: " + alvo.getVida() + " pontos de vida restantes.");
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
    public void mostrarInformacoes() {
        super.mostrarInformacoes();
        System.out.println("Mana: "+this.mana);
        System.out.println("Dano Mágico: " + this.danoMagico);
    }

    public int getDanoMagico() {
        return danoMagico;
    }

    public void setDanoMagico(int danoMagico) {
        this.danoMagico = danoMagico;
    }
}