package player;

import java.util.List;
import java.util.Scanner;

public class Caminhos{
    public void caminho(){
        int dano;
        Personagem personagem = null;
        LeitorInimigos leitor = new LeitorInimigos();
        List<Inimigo> inimigos = leitor.lerInimigos("src/dados/inimigos");

        Scanner scan = new Scanner(System.in);
        System.out.println("Escolha sua classe: ");
        System.out.println("1. Mago");
        int op = scan.nextInt();

        switch(op){
            case 1: {
                personagem = new Mago();
                break;
            }
        }

        System.out.println("Escolha o nome do personagem: ");
        personagem.setNome(scan.next());

        Inimigo inimigoAtual = inimigos.get(0);
        System.out.println("Um " + inimigoAtual.getNome() + " Apareceu!");


        do{
            System.out.println("O que gostaria de fazer? ");
            System.out.println("1. Ataque físico.");
            System.out.println("2. Defender.");

            if(personagem instanceof Mago){
                System.out.println("3. Ataque mágico.");
            }

            op = scan.nextInt();

            switch(op){
                case 1: {
                    personagem.atacar(inimigoAtual);;
                    break;
                }
                case 3: {
                    if (personagem instanceof Mago) {
                        ((Mago) personagem).atacarMagia(inimigoAtual);
                    }
                    break;
                }
            }
            if(inimigoAtual.verificarMorte()){
                System.out.println("Você derrotou o inimigo!");
                personagem.verificarUpNivel(inimigoAtual);
                break;
            }
        }while (op != 0);
    }
}