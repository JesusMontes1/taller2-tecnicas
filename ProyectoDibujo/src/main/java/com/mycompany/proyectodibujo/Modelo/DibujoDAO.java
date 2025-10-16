/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodibujo.Modelo;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class DibujoDAO {
    private ArrayList<BufferedImage> memoria = new ArrayList<>();
    private ArrayList<String> nombres = new ArrayList<>();
    
    public void registrarEnMemoria(BufferedImage i, String nombre) {
        memoria.add(i);
        nombres.add(nombre);
    }
    
    public void cambiarMemoria(int index, BufferedImage i) {
        memoria.set(index, i);
    }
    
    public void cambiarMemoria(int index, BufferedImage i, String nombre) {
        memoria.set(index, i);
        nombres.set(index, nombre);
    }
    
    public BufferedImage getMemoria(int index) {
        return memoria.get(index);
    }
    
    public String getNombre(int index) {
        return nombres.get(index);
    }
    
    public boolean consultarNombres(String s) {
        return nombres.contains(s);
    }
    
    public int obtenerIndexNombres(String s) {
        return nombres.indexOf(s);
    }
}
