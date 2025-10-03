/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistematransporte;

/**
 *
 * @author jesus.montes1
 */
public class Teleferico implements SistemaTarifario {

    @Override
    public double calcularTarifa(int distancia) {
        return 1000.0 + distancia * 500.0;
    }

    @Override
    public void mostrarRuta() {
        System.out.println("Ruta de conexión veredal (tarifa variable, base $1000 COP)");
    }
    
}
