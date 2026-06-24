package player;

import Itens.*;
import Itens.PocaoVida;
import Itens.PocaoMana;

public abstract class Personagem implements java.io.Serializable{
    private boolean subiuNivel = false;
    private int pontosUp;
    private String nome;
    private int vida;
    private int vidaMaxima = 100;
    private int mana;
    private int manaMaxima = 50;
    private int ataque;
    private int defesa;
    private int nivel;
    private int xp;
    private int id;
    private static int contador = 0;
    private Item armaEquipada = null;
    private Item armaduraEquipada = null;
    private int moedas;

    private Inventario inventario = new Inventario();

    private boolean defendendo = false;

    public Personagem() {
        this.id = ++contador;
        this.vida =
                50;
        this.vidaMaxima = 100;
        this.mana = 50;
        this.manaMaxima = 50;
        this.nivel = 1;
        this.xp = 0;
        this.moedas = 100;
    }

    public Personagem(String nome, int nivel, int vida, int ataque, int defesa, int xp) {
        this.id = ++contador;
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.mana = 50;
        this.manaMaxima = 50;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.xp = xp;
        this.moedas = 100;
    }

    public void usarPocao(Item pocao) {
        if (pocao instanceof PocaoVida) {
            PocaoVida pv = (PocaoVida) pocao;
            this.vida += pv.getRecuperacao();

            if (this.vida > this.vidaMaxima) {
                this.vida = this.vidaMaxima;
            }
            System.out.println("[ITEM] Você bebeu " + pv.getNome() + " e recuperou " + pv.getRecuperacao() + " de Vida!");
            this.inventario.removerItem(pocao);

        } else if (pocao instanceof PocaoMana) {
            if (this.manaMaxima == 0) {
                System.out.println("[AVISO] " + this.getNome() + " não utiliza mana! Você não pode usar esta poção.");
                return;
            }

            PocaoMana pm = (PocaoMana) pocao;
            this.mana += pm.getRecuperacao();

            if (this.mana > this.manaMaxima) {
                this.mana = this.manaMaxima;
            }
            System.out.println("[ITEM] Você bebeu " + pm.getNome() + " e recuperou " + pm.getRecuperacao() + " de Mana!");
            this.inventario.removerItem(pocao);
        }
    }

    public abstract void atacar(Personagem alvo);

    public boolean isDefendendo() {
        return defendendo;
    }

    public void setDefendendo(boolean defendendo) {
        this.defendendo = defendendo;
    }

    public void verificarUpNivel(Inimigo alvo) {
        boolean subiuNivel = false;
        this.xp += alvo.getXp();
        System.out.println("Você recebeu " + alvo.getXp() + " de XP do " + alvo.getNome() + "!");

        if (this.xp >= 100) {
            subiuNivel = true;
            this.nivel++;
            this.pontosUp = 5;
            this.xp = this.xp - 100;
            System.out.println("Você subiu de nível! Seu nível atual: " + this.nivel);
            System.out.println("Você ganhou 5 pontos para aumentar seus status como recompensa!");
        } else {
            System.out.println("XP atual: " + this.xp);
        }
    }

    public void equiparItem(Item item) {
        if (item instanceof Arma) {
            Arma novaArma = (Arma) item;

            if (this.armaEquipada != null) {
                Arma antiga = (Arma) this.armaEquipada;
                this.setAtaque(this.getAtaque() - antiga.getDano());
            }
            this.armaEquipada = novaArma;
            this.setAtaque(this.getAtaque() + novaArma.getDano());
            System.out.println("[EQUIPADO] Você equipou " + novaArma.getNome() + "! Ataque aumentado.");

        } else if (item instanceof Equipamento) {
            Equipamento novoEquipamento = (Equipamento) item;

            if (this.armaduraEquipada != null) {
                Equipamento antiga = (Equipamento) this.armaduraEquipada;
                this.setDefesa(this.getDefesa() - antiga.getDefesa());
            }

            this.armaduraEquipada = novoEquipamento;
            this.setDefesa(this.getDefesa() + novoEquipamento.getDefesa());
            System.out.println("[EQUIPADO] Você vestiu " + novoEquipamento.getNome() + "! Defesa aumentada.");
        }
    }

    public abstract void aumentarStatus();

    public void mostrarInformacoes() {
        System.out.println("Dano Físico: " + getAtaque());
        System.out.println("Defesa: " + getDefesa());
        System.out.println("Vida: " + getVida() + "/" + vidaMaxima);
        System.out.println("Mana: " + getMana() + "/" + manaMaxima);
    }

    public int getMoedas() {
        return this.moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public Item getArmaEquipada() {
        return armaEquipada;

    }
    public Item getArmaduraEquipada() {
        return armaduraEquipada;

    }

    public Inventario getInventario() {
        return inventario;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) this.mana = 0;
        else this.mana = mana;
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
        if(vida < 0) {
            this.vida = 0;
        }else {
            this.vida = vida;
        }
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public void setManaMaxima(int manaMaxima) {
        this.manaMaxima = manaMaxima;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public int getPontosUp() {
        return pontosUp;
    }

    public void setPontosUp(int pontosUp) {
        this.pontosUp = pontosUp;
    }

    public boolean isSubiuNivel() {
        return subiuNivel;
    }

    public void setSubiuNivel(boolean subiuNivel) {
        this.subiuNivel = subiuNivel;
    }
}