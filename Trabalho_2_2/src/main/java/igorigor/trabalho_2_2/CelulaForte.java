/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package igorigor.trabalho_2_2;

/**
 *
 * @author The Jarbas
 */
public class CelulaForte extends CelulaAbstrata {

    @Override
    public boolean vaiMorrer(int vizinhos) {
        return vizinhos < 2;
    }

    @Override
    public boolean vaiNascer(int vizinhos) {
        return vizinhos > 4;
    }

    @Override
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
}

