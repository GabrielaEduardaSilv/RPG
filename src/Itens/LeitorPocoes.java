package Itens;

import dados.ArquivoInvalidoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorPocoes {
    // Mudamos o retorno de List<PocaoVida> para List<Item> para aceitar qualquer item!
    public List<Item> lerPocoes(String arquivo) {
        List<Item> pocoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            br.readLine();

            while((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");

                if(dados.length != 5) {
                    throw new ArquivoInvalidoException("Linha inválida: " + linha);
                }

                int id = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                int recuperacao = Integer.parseInt(dados[2].trim());
                RaridadeItem raridade = RaridadeItem.valueOf(dados[3].trim().toUpperCase());
                int valor = Integer.parseInt(dados[4].trim());

                if (nome.toLowerCase().contains("mana")) {
                    PocaoMana pocaoMana = new PocaoMana(id, nome, recuperacao, raridade, valor);
                    pocoes.add(pocaoMana);
                } else {
                    PocaoVida pocaoVida = new PocaoVida(id, nome, recuperacao, raridade, valor);
                    pocoes.add(pocaoVida);
                }
            }
        } catch(ArquivoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch(IOException | IllegalArgumentException e) {
            System.out.println("Erro ao ler o arquivo de poções: " + e.getMessage());
        }
        return pocoes;
    }
}