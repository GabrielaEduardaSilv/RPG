package player;

import java.util.Scanner;

public class Caminhos {
    public void caminho() {
        Scanner scan = new Scanner(System.in);
        Batalha combate = new Batalha();
        int op;
        boolean executandoJogo = true;

        while (executandoJogo) {
            System.out.println("-------------------------");
            System.out.println("   A aventura do herói   ");
            System.out.println("-------------------------");
            System.out.println("1. Iniciar nova aventura");
            System.out.println("2. Carregar aventura");
            System.out.println("3. Sair do jogo");
            System.out.println("-------------------------");
            System.out.println("Escolha uma opção: ");
            int opMenu = scan.nextInt();

            switch(opMenu){
                case 1: {
                    Personagem personagem = null;

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

                    combate.batalha(personagem);

                    break;
                }
                case 2: {
                    combate.carregarJogo(scan);
                    break;
                }
                case 3:{
                    System.out.println("Saindo...");
                    executandoJogo = false;
                    break;
                }
                default:{
                    System.out.println("Opção inválida!");
                }
            }
        }
    }
}