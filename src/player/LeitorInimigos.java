package player;


import dados.ArquivoInvalidoException;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorInimigos {
    public List<Inimigo> lerInimigos(String arquivo) {
        List<Inimigo> inimigos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length != 6) {
                    throw new ArquivoInvalidoException("Linha inválida: " + linha);
                }

                Inimigo inimigo = new Inimigo(
                        dados[0],
                        Integer.parseInt(dados[1]),
                        Integer.parseInt(dados[2]),
                        Integer.parseInt(dados[3]),
                        Integer.parseInt(dados[4]),
                        Integer.parseInt(dados[5])
                );
                inimigos.add(inimigo);
            }
        } catch (ArquivoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return inimigos;
    }
}