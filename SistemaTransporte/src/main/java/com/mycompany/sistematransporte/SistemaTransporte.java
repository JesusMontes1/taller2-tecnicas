/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistematransporte;

/**
 *
 * @author jesus.montes1
 */
public class SistemaTransporte {
    
    public static void procesarViaje(SistemaTarifario vehiculo, int km) {
        vehiculo.mostrarRuta();
        
        System.out.println(vehiculo.calcularTarifa(km));
    }

    public static void main(String[] args) {
        BusArticulado bus = new BusArticulado();
        Teleferico tele = new Teleferico();
        
        procesarViaje(bus, 5);
        procesarViaje(tele, 2);
    }
}
