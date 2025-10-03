/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistematransporte;

/**
 *
 * @author jesus.montes1
 */
public class BusArticulado implements SistemaTarifario {
    
    @Override
    public double calcularTarifa(int distancia) {
        return 2590.0;
    }

    @Override
    public void mostrarRuta() {
        System.out.println("Ruta troncal (estándar de $2950 COP)");
    }
}
