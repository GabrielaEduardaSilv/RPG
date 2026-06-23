package player;

public abstract class Personagem {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private int nivel;
    private int xp;
    private int id;
    private static int contador = 0;

    public Personagem() {
        this.id = ++contador;
        this.vida = 100;
        this.nivel = 1;
        this.xp = 0;
    }

    public Personagem(String nome, int nivel, int vida, int ataque, int defesa, int xp) {
        this.id = ++contador;
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.xp = xp;
    }

    public abstract void atacar(Personagem alvo);

    private boolean defendendo = false;

    public boolean isDefendendo() {
        return defendendo;
    }

    public void setDefendendo(boolean defendendo) {
        this.defendendo = defendendo;
    }

    public void verificarUpNivel(Inimigo alvo) {
        this.xp += alvo.getXp();
        System.out.println("Você recebeu " + alvo.getXp() + " de XP do " + alvo.getNome() + "!");

        if (this.xp >= 100) {
            this.nivel++;
            this.xp = this.xp - 100;
            System.out.println("Você subiu de nível! Seu nível atual: " + this.nivel);
        } else {
            System.out.println("XP atual: " + this.xp);
        }
    }
    public void mostrarInformacoes(){
        System.out.println("Dano Físico: "+getAtaque());
        System.out.println("Defesa: "+getDefesa());
        System.out.println("Vida: "+getVida());

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getId() {
        return id;
    }
}