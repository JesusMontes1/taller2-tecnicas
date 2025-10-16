/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodibujo.Modelo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;



/**
 *
 * @author jesus
 */
public class DibujoModelo {
    private final int DM_WIDTH = 365;
    private final int DM_HEIGHT = 255;
    
    private int indexActual = -1;
    
    private DibujoDAO dao = new DibujoDAO();
    
    private ModoCursor modoActual = ModoCursor.PINCEL;
    
    private Color colorActual = Color.BLACK;

    public ModoCursor getModoActual() {
        return modoActual;
    }

    public void setModoActual(ModoCursor modoActual) {
        this.modoActual = modoActual;
    }

    public Color getColorActual() {
        return colorActual;
    }

    public void setColorActual(Color colorActual) {
        this.colorActual = colorActual;
    }
    
    public boolean esModoActualManoLibre() {
        return modoActual == ModoCursor.PINCEL || modoActual == ModoCursor.BORRADOR;
    }

    public int getWidth() {
        return DM_WIDTH;
    }

    public int getHeight() {
        return DM_HEIGHT;
    }

    public int getIndexActual() {
        return indexActual;
    }

    public void setIndexActual(int indexActual) {
        this.indexActual = indexActual;
    }
    
    public BufferedImage copiarDibujo(BufferedImage origen) {
        BufferedImage copia = new BufferedImage(DM_WIDTH, DM_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) copia.getGraphics();
        g.drawImage(origen, 0, 0, null);
        g.dispose();
        return copia;
    }
    
    public void guardar(BufferedImage i, String s) {
        dao.registrarEnMemoria(copiarDibujo(i), s);
    }
    
    public void sobreescribir(int index, BufferedImage i) {
        dao.cambiarMemoria(index, i);
    }
    
    public BufferedImage cargar(int index) {
        return dao.getMemoria(index);
    }
    
    public boolean consultarNombres(String s) {
        return dao.consultarNombres(s);
    }
    
    public int obtenerIndexNombres(String s) {
        return dao.obtenerIndexNombres(s);
    }
}
