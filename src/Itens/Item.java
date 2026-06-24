package Itens;

public abstract class Item {
    private int id;
    private String nome;
    private RaridadeItem raridade;
    private int valor;

    public Item(int id, String nome, RaridadeItem raridade, int valor, TipoItem tipoItem) {
        this.id = id;
        this.nome = nome;
        this.raridade = raridade;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RaridadeItem getRaridade() {
        return raridade;
    }

    public void setRaridade(RaridadeItem raridade) {
        this.raridade = raridade;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}