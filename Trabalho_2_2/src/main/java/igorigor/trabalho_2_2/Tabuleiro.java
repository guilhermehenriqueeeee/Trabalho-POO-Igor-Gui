
package igorigor.trabalho_2_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tabuleiro {

    CelulaClassica celulaClassica = new CelulaClassica();
    CelulaForte celulaForte = new CelulaForte();
    CelulaMatematica celulaMatematica = new CelulaMatematica();
    CelulaTimida celulaTimida = new CelulaTimida();

    private int linhas = 0, colunas = 0, iteracoes = 0;
    private char[][] matrizCelulas;
    private char[][] novaMatrizCelulas;
    private int[][] matrizEstados;
    private int[][] novaMatrizEstados;
    private char[][] matrizAuxiliar;
    private int vizinhos;
    private int[][] matrizEstadosInicial;

    public Tabuleiro() {
        celulaClassica.setSimbolo("+");
        celulaForte.setSimbolo("@");
        celulaMatematica.setSimbolo("#");
        celulaTimida.setSimbolo("&");
    }
    
    public Tabuleiro(File arquivo) throws FileNotFoundException, InputMismatchException {
        
        Scanner scanner = new Scanner(arquivo);
        
        try { 
            this.linhas = scanner.nextInt(); 
            this.colunas = scanner.nextInt(); 
            
            inicializaMatrizes(); 
            
            scanner.nextLine(); 
            
            for(int i = 1; i <= linhas; i++){ 
                if (!scanner.hasNextLine()) { 
                    throw new InputMismatchException("Arquivo inválido: linhas de matriz de caracteres insuficientes.");
                }
                String linhaCaracteres = scanner.nextLine(); 
                if (linhaCaracteres.length() < colunas) { 
                     throw new InputMismatchException("Arquivo inválido: linha de caracteres " + i + " menor que o esperado.");
                }
                for(int j = 1; j <= colunas; j++){ 
                    this.matrizCelulas[i][j] = linhaCaracteres.charAt(j - 1); 
                }
            }
            
            for(int i = 1; i <= linhas; i++){ 
                for(int j = 1; j <= colunas; j++){ 
                    if (!scanner.hasNextInt()) { 
                        throw new InputMismatchException("Arquivo inválido: dados de matriz de estados insuficientes ou formato incorreto na posição [" + i + "][" + j + "].");
                    }
                    this.matrizEstados[i][j] = scanner.nextInt(); 
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Erro de formato de arquivo: " + e.getMessage());
            throw e; 
        } finally {
            scanner.close(); 
        }
        
        matrizEstadosInicial();
    }
    
    public void matrizEstadosInicial(){
        for(int i = 1; i <= linhas; i++){
            for(int j = 1; j <= colunas; j++){
                matrizEstadosInicial[i][j] = matrizEstados[i][j];
            }
        }
    }
    
    public String paraArquivo(char[][] matrizCelulas, int[][]matrizEstados){
        StringBuilder resultado = new StringBuilder();

        for(int i = 1; i <= linhas; i++){ 
            for(int j = 1; j <= colunas; j++){
                if(matrizEstados[i][j] == 1){
                    resultado.append(matrizCelulas[i][j]); 
            }else{
                    resultado.append('.');
                }
        }
            if(i < linhas)
                resultado.append("\n"); 
        
    }
        return resultado.toString();
    }
    
    public String paraArquivo2(char[][] matrizCelulas){
        StringBuilder resultado = new StringBuilder();

        for(int i = 1; i <= linhas; i++){ 
            for(int j = 1; j <= colunas; j++){
                    resultado.append(matrizCelulas[i][j]); 
                }
            if(i < linhas)
                resultado.append("\n"); 
        
    }
        return resultado.toString();
    }
    
    public String retornaMatrizEstados(){
        StringBuilder resultado = new StringBuilder();
        for(int i = 1; i <= linhas; i++){
            for(int j = 1; j <= colunas; j++){
                resultado.append(matrizEstados[i][j]).append(" ");
            }
            resultado.append("\n");
        }
        
        return resultado.toString();
    }
    
    public String retornaMatrizEstadosInicial(){
        StringBuilder resultado = new StringBuilder();
        for(int i = 1; i <= linhas; i++){
            for(int j = 1; j <= colunas; j++){
                resultado.append(matrizEstadosInicial[i][j]).append(" ");
            }
            resultado.append("\n");
        }
        
        return resultado.toString();
    }
    

    public void setLinhas(int linhas) {
        this.linhas = linhas;
        inicializaMatrizes();
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
        inicializaMatrizes();
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }

    public int getIteracoes() {
        return iteracoes;
    }

    private void inicializaMatrizes() {
        matrizCelulas = new char[linhas + 2][colunas + 2];
        novaMatrizCelulas = new char[linhas + 2][colunas + 2];
        matrizEstados = new int[linhas + 2][colunas + 2];
        novaMatrizEstados = new int[linhas + 2][colunas + 2];
        matrizAuxiliar = new char[linhas + 2][colunas + 2];
        matrizEstadosInicial = new int[linhas + 2][colunas + 2];
    }

    public void completaMatrizes() {
        for (int i = 0; i < linhas + 2; i++) {
            for (int j = 0; j < colunas + 2; j++) {
                if (i == 0 || i == linhas + 1 || j == 0 || j == colunas + 1) {
                    matrizCelulas[i][j] = '.';
                    matrizEstados[i][j] = 0;
                }
            }
        }
    }

    public void primeiraImpressao() {
        for (int l = 0; l <= linhas + 1; l++) {
            for (int c = 0; c <= colunas + 1; c++) {
                if (matrizEstados[l][c] == 0) {
                    System.out.print(". ");
                }else{
                    System.out.print(matrizCelulas[l][c] + " ");
                }
            }
            System.out.print("\n");
        }
    }
    
    public void impressaoGeral(){
        for(int i = 0; i <= linhas + 1; i++){
            for(int j = 0; j <= colunas + 1; j++){
                if(matrizEstados[i][j] == 0){
                    System.out.print(". ");
                }else{
                    System.out.print(matrizCelulas[i][j] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    public void logica() {
    for (int i = 1; i <= linhas; i++) {
        for (int j = 1; j <= colunas; j++) {
            switch (matrizCelulas[i][j]) {
                case '+':
                    vizinhos = celulaClassica.contaVizinho(matrizEstados, i, j);

                    if (matrizEstados[i][j] == 0) {
                        if (celulaClassica.vaiNascer(vizinhos)) {
                            novaMatrizCelulas[i][j] = '+';
                            novaMatrizEstados[i][j] = 1;
                        } else {
                            novaMatrizCelulas[i][j] = '+';
                            novaMatrizEstados[i][j] = 0;
                        }
                    } else {
                        if (celulaClassica.vaiMorrer(vizinhos)) {
                            novaMatrizCelulas[i][j] = '+';
                            novaMatrizEstados[i][j] = 0;
                        } else {
                            novaMatrizCelulas[i][j] = '+';
                            novaMatrizEstados[i][j] = 1;
                        }
                    }
                    break;

                case '@':
                    vizinhos = celulaForte.contaVizinho(matrizEstados, i, j);

                    if (matrizEstados[i][j] == 0) {
                        if (celulaForte.vaiNascer(vizinhos)) {
                            novaMatrizCelulas[i][j] = '@';
                            novaMatrizEstados[i][j] = 1;
                        } else {
                            novaMatrizCelulas[i][j] = '@';
                            novaMatrizEstados[i][j] = 0;
                        }
                    } else {
                        if (celulaForte.vaiMorrer(vizinhos)) {
                            novaMatrizCelulas[i][j] = '@';
                            novaMatrizEstados[i][j] = 0;
                        } else {
                            novaMatrizCelulas[i][j] = '@';
                            novaMatrizEstados[i][j] = 1;
                        }
                    }
                    break;

                case '#':
                    vizinhos = celulaMatematica.contaVizinho(matrizEstados, i, j);

                    if (matrizEstados[i][j] == 0) {
                        if (celulaMatematica.vaiNascer(vizinhos)) {
                            novaMatrizCelulas[i][j] = '#';
                            novaMatrizEstados[i][j] = 1;
                        } else {
                            novaMatrizCelulas[i][j] = '#';
                            novaMatrizEstados[i][j] = 0;
                        }
                    } else {
                        if (celulaMatematica.vaiMorrer(vizinhos)) {
                            novaMatrizCelulas[i][j] = '#';
                            novaMatrizEstados[i][j] = 0;
                        } else {
                            novaMatrizCelulas[i][j] = '#';
                            novaMatrizEstados[i][j] = 1;
                        }
                    }
                    break;

                case '&':
                    vizinhos = celulaTimida.contaVizinho(matrizEstados, i, j);

                    if (matrizEstados[i][j] == 0) {
                        if (celulaTimida.vaiNascer(vizinhos)) {
                            novaMatrizCelulas[i][j] = '&';
                            novaMatrizEstados[i][j] = 1;
                        } else {
                            novaMatrizCelulas[i][j] = '&';
                            novaMatrizEstados[i][j] = 0;
                        }
                    } else {
                        if (celulaTimida.vaiMorrer(vizinhos)) {
                            novaMatrizCelulas[i][j] = '&';
                            novaMatrizEstados[i][j] = 0;
                        } else {
                            novaMatrizCelulas[i][j] = '&';
                            novaMatrizEstados[i][j] = 1;
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }
}

    public void copiaMatrizes() {
    for (int i = 0; i < linhas + 2; i++) {
        for (int j = 0; j < colunas + 2; j++) {
            if (i == 0 || i == linhas + 1 || j == 0 || j == colunas + 1) {
                matrizCelulas[i][j] = '.';
                matrizEstados[i][j] = 0;
            } else {
                matrizCelulas[i][j] = novaMatrizCelulas[i][j];
                matrizEstados[i][j] = novaMatrizEstados[i][j];
            }
        }
    }
}

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
    

    public char[][] getMatrizCelulas() {
        return matrizCelulas;
    }

    public char[][] getNovaMatrizCelulas() {
        return novaMatrizCelulas;
    }

    public int[][] getMatrizEstados() {
        return matrizEstados;
    }

    public int[][] getNovaMatrizEstados() {
        return novaMatrizEstados;
    }

    public char[][] getMatrizAuxiliar() {
        return matrizAuxiliar;
    }

    public int getVizinhos() {
        return vizinhos;
    }

    public int[][] getMatrizEstadosInicial() {
        return matrizEstadosInicial;
    }

    public void setMatrizEstadosInicial(int[][] matrizEstadosInicial) {
        this.matrizEstadosInicial = matrizEstadosInicial;
    }

    public void setMatrizCelulas(char[][] matrizCelulas) {
        this.matrizCelulas = matrizCelulas;
    }

    public void setNovaMatrizCelulas(char[][] novaMatrizCelulas) {
        this.novaMatrizCelulas = novaMatrizCelulas;
    }

    public void setMatrizEstados(int[][] matrizEstados) {
        this.matrizEstados = matrizEstados;
    }

    public void setNovaMatrizEstados(int[][] novaMatrizEstados) {
        this.novaMatrizEstados = novaMatrizEstados;
    }

    public void setMatrizAuxiliar(char[][] matrizAuxiliar) {
        this.matrizAuxiliar = matrizAuxiliar;
    }

    public void setVizinhos(int vizinhos) {
        this.vizinhos = vizinhos;
    }
    
    
    
    
    
}


    
    
    
    

    
    
    
    

