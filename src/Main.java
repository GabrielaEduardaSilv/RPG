/**
 * -----------------------------------------------------------------------
 * FURB - UNIVERSIDADE REGIONAL DE BLUMENAU
 * CURSO: SISTEMA DE INFORMAÇÃO
 * DISCIPLINA: Programação Orientada a Objetos (POO)
 * * PROJETO: RPG de Turno (Sistema de Combate, Inventário e Mercador)
 * AUTOR: CRYSTIAN KROPLIN
 * RGM: 226182
 * DATA: 23/06/2026
 * -----------------------------------------------------------------------
 */
import Itens.*;
import player.Caminhos;
import player.Inimigo;
import player.LeitorInimigos;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Carregando banco de dados do RPG...");
            System.out.println("-----------------------------------------------------------------------");

            Loja mercador = new Loja();
            LeitorArmas leitorArmas = new LeitorArmas();
            LeitorEquipamentos leitorEquips = new LeitorEquipamentos();
            LeitorPocoes leitorPocoes = new LeitorPocoes();
            LeitorInimigos leitorInimigos = new LeitorInimigos();

            System.out.print("-> Carregando armas... ");
            List<Arma> armasValidadas = leitorArmas.lerArmas("src/Itens/armas");
            System.out.println("[OK] (" + armasValidadas.size() + " carregadas)");

            System.out.print("-> Carregando equipamentos... ");
            List<Equipamento> equipsValidados = leitorEquips.lerEquipamentos("src/Itens/equipamentos");
            System.out.println("[OK] (" + equipsValidados.size() + " carregados)");

            System.out.print("-> Carregando poções... ");
            List<Item> pocoesValidadas = leitorPocoes.lerPocoes("src/Itens/pocoes");

            System.out.print("-> Abastecendo o estoque do Mercador... ");
            mercador.gerarItensAleatorios();
            System.out.println("[OK]");

            System.out.print("-> Carregando bestiário de inimigos... ");
            List<Inimigo> inimigosValidados = leitorInimigos.lerInimigos("src/dados/inimigos");
            System.out.println("[OK] (" + inimigosValidados.size() + " monstros prontos para o combate)");

            System.out.println("[SUCESSO] Todos os dados foram carregados corretamente! Bom jogo.");

        } catch (Exception e) {
            System.out.println("[CRÍTICO] Falha ao iniciar o banco de dados do jogo!");
            System.out.println("[AVISO] Verifique se os arquivos 'armas.txt', 'equipamentos.txt' e 'pocoes.txt' estão na pasta raiz do projeto.");
            System.out.println("[DETALHE DO ERRO] " + e.getMessage());

            System.exit(1);
        }

        Caminhos caminhos = new Caminhos();
        caminhos.caminho();
    }
}