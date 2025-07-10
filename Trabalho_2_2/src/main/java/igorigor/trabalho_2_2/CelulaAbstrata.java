/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package igorigor.trabalho_2_2;

/**
 *
 * @author The Jarbas
 */
public abstract class CelulaAbstrata {
    
    protected int qtdVizinhos;

    protected String simbolo;

    public int contaVizinho(int[][] matriz, int posicaoLinha, int posicaoColuna) {
        qtdVizinhos = 0; // Resetar a contagem de vizinhos
        for (int i = posicaoLinha - 1; i <= posicaoLinha + 1; i++) {
            for (int j = posicaoColuna - 1; j <= posicaoColuna + 1; j++) {
                if (i == posicaoLinha && j == posicaoColuna) {
                    continue;
                }
                if (i >= 0 && i < matriz.length && j >= 0 && j < matriz[0].length && matriz[i][j] == 1) {
                    qtdVizinhos++;
                }
            }
        }
        return qtdVizinhos;
    }

    public abstract boolean vaiMorrer(int vizinhos);

    public abstract boolean vaiNascer(int vizinhos);

    public abstract void setSimbolo(String simbolo);

    public int getVizinhos() {
        return qtdVizinhos;
    }
}
