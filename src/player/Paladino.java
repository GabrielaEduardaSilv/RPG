package player;

public class Paladino extends Personagem {
    private int mana = 10;
    private int danoMagico = 12;

    public Paladino() {
        super();
        setAtaque(10);
        setDefesa(13);
    }

    public int getDanoMagico() {
        return danoMagico;
    }

    public void setDanoMagico(int danoMagico) {
        this.danoMagico = danoMagico;
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

        if (alvo.isDefendendo()) {
            getAtaque();
        }

        alvo.setVida(alvo.getVida() - this.getAtaque());
        if (alvo.getVida() < 0) {
            alvo.setVida(0);
        }
        System.out.println("Você causou: " + getAtaque() + " de dano, o inimigo tem: " + alvo.getVida() + " pontos de vida restantes.");
    }
    @Override
    public void mostrarInformacoes() {
        super.mostrarInformacoes();
        System.out.println("Mana: "+this.mana);
        System.out.println("Dano Mágico: " + this.danoMagico);
    }

}