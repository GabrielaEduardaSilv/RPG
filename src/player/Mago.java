package player;

public class Mago extends Personagem{
    private int mana = 20;
    private int danoMagico = 15;

    public Mago(){
        super();
        setAtaque(5);
        setDefesa(5);
    }

    public void atacarMagia(Personagem alvo){
        alvo.setVida(alvo.getVida() - danoMagico);
        if (alvo.getVida() < 0) {
            alvo.setVida(0);
        }
        System.out.println("Você causou: " + danoMagico + " de dano mágico, o inimigo tem: " + alvo.getVida() + " pontos de vida restantes.");
    }

    @Override
    public void atacar(Personagem alvo){

        if(alvo.isDefendendo()){
            getAtaque();
        }

        alvo.setVida(alvo.getVida() - this.getAtaque());
        if (alvo.getVida() < 0) {
            alvo.setVida(0);
        }
        System.out.println("Você causou: " + getAtaque() + " de dano, o inimigo tem: " + alvo.getVida() + " pontos de vida restantes.");
    }

    public int getDanoMagico(){
        return danoMagico;
    }

    public void setDanoMagico(int danoMagico){
        this.danoMagico = danoMagico;
    }
}