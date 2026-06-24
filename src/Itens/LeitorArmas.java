package Itens;

import dados.ArquivoInvalidoException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorArmas {
    public List<Arma> lerArmas(String arquivo) {
        List<Arma> armas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            br.readLine();

            while((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");

                if(dados.length != 5) {
                    throw new ArquivoInvalidoException("Linha inválida: " + linha);
                }

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1].trim();
                int dano = Integer.parseInt(dados[2].trim());
                RaridadeItem raridade = RaridadeItem.valueOf(dados[3].trim().toUpperCase());
                int valor = Integer.parseInt(dados[4].trim());

                Arma arma = new Arma(id, nome, dano, raridade, valor);
                armas.add(arma);
            }
        } catch(ArquivoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch(IOException | IllegalArgumentException e) {
            System.out.println("Erro ao ler o arquivo de poções: " + e.getMessage());
        }
        return armas;
    }
}
