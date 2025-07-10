
package igorigor.trabalho_2_2;

import java.util.Scanner;

public class Trabalho_2_2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();

        /*System.out.println("Insira as dimensoes (m x n) do tabuleiro: ");
        tabuleiro.setLinhas(scanner.nextInt());
        tabuleiro.setColunas(scanner.nextInt());
        
        char[][] matrizCelulas = new char[tabuleiro.getLinhas() + 2][tabuleiro.getColunas() + 2];
        int[][] matrizEstados = new int[tabuleiro.getLinhas() + 2][tabuleiro.getColunas() + 2];

        System.out.println("Insira os caracteres de cada celula do tabuleiro: ");
        for (int i = 1; i <= tabuleiro.getLinhas(); i++) {
            for (int j = 1; j <= tabuleiro.getColunas(); j++) {
                matrizCelulas[i][j] = scanner.next().charAt(0);
            }
        }
        tabuleiro.setMatrizCelulas(matrizCelulas);

        System.out.println("Insira se estao vivas ou mortas: ");
        for (int i = 1; i <= tabuleiro.getLinhas(); i++) {
            for (int j = 1; j <= tabuleiro.getColunas(); j++) {
                matrizEstados[i][j] = scanner.nextInt();
            }
        }
        tabuleiro.setMatrizEstados(matrizEstados);
        
        tabuleiro.completaMatrizes(); 
        
        tabuleiro.matrizEstadosInicial();

        System.out.println("Insira o numero de iteracoes: ");
        tabuleiro.setIteracoes(scanner.nextInt());

        System.out.println("Estado inicial do tabuleiro:");
        tabuleiro.primeiraImpressao();
        System.out.print("\n");

        for (int i = 0; i < tabuleiro.getIteracoes(); i++) {

            tabuleiro.logica();
            
            tabuleiro.copiaMatrizes();

            System.out.println("\nIteracao: " + (i + 1));
            tabuleiro.impressaoGeral();
        }*/
    
        TelaTabuleiro telaTabuleiro = new TelaTabuleiro(tabuleiro);
    }
    
}
