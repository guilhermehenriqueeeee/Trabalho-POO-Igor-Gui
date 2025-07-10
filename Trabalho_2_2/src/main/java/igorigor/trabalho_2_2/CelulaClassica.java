/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


// CelulaForte.java
package igorigor.trabalho_2_2;

public class CelulaClassica extends CelulaAbstrata {

    @Override
    public boolean vaiMorrer(int vizinhos) {
        return vizinhos < 2 || vizinhos > 3;
    }

    @Override
    public boolean vaiNascer(int vizinhos) {
        return vizinhos == 3;
    }

    @Override
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
}

