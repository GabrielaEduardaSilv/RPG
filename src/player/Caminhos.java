package player;

import java.util.List;
import java.util.Scanner;

public class Caminhos {
    public void caminho() {
        int dano;
        Personagem personagem = null;
        Scanner scan = new Scanner(System.in);

        int op;

        do {
            System.out.println("Escolha sua classe: ");
            System.out.println("1. Mago");
            System.out.println("2. Bárbaro");
            System.out.println("3. Paladino");
            op = scan.nextInt();
            switch (op) {
                case 1: {
                    personagem = new Mago();
                    personagem.mostrarInformacoes();
                    System.out.println("Você confirma sua escolha?");
                    System.out.println("1- Confirmar | 2-Negar ");
                    op = scan.nextInt();
                    if (op == 1) {
                        System.out.println("Escolha confirmada");
                    } else if (op == 2) {
                        personagem = null;
                        break;
                    } else {
                        personagem = null;
                        System.out.println("Opção invalida");
                        break;
                    }
                    break;
                }
                case 2: {
                    personagem = new Barbaro();
                    personagem.mostrarInformacoes();
                    System.out.println("Você confirma sua escolha?");
                    System.out.println("1- Confirmar | 2-Negar ");
                    op = scan.nextInt();
                    if (op == 1) {
                        System.out.println("Escolha confirmada");
                    } else if (op == 2) {
                        personagem = null;
                        break;
                    } else {
                        personagem = null;
                        System.out.println("Opção invalida");
                        break;
                    }
                    break;
                }
                case 3: {
                    personagem = new Paladino();
                    personagem.mostrarInformacoes();
                    System.out.println("Você confirma sua escolha?");
                    System.out.println("1- Confirmar | 2-Negar ");
                    op = scan.nextInt();
                    if (op == 1) {
                        System.out.println("Escolha confirmada");
                    } else if (op == 2) {
                        personagem = null;
                        break;
                    } else {
                        personagem = null;
                        System.out.println("Opção invalida");
                        break;
                    }
                    break;
                }
            }
        } while (op != 1);
        System.out.println("Escolha o nome do personagem: ");
        personagem.setNome(scan.next());

        Batalha combate = new Batalha();
        combate.batalha(personagem);
    }
}