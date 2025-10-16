package com.mycompany.proyectodibujo;

import com.mycompany.proyectodibujo.Modelo.DibujoModelo;
import com.mycompany.proyectodibujo.Modelo.ModoCursor;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/BeanForm.java to edit this template
 */

/**
 *
 * @author jesus
 * 
 * Este es el Controlador, solo que con otro nombre.
 */

public class PanelDibujo extends JPanel {
    
    
    private ArrayList<BufferedImage> historial = new ArrayList<>();
    
    private int historialActual = 0;
    
    private BufferedImage dibujo, imagenHistorial;
    
    private DibujoModelo dm = new DibujoModelo();
    
    private boolean guardado = false;
    
    private int x1, y1, x2, y2;
    
    public PanelDibujo() {
        super();
        
        while (historial.size() < 50) { historial.add(null); };
                
        setBackground(Color.WHITE);
                
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                
                if (dm.esModoActualManoLibre()) {
                    dibujar();
                }
            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                registrarDibujo();
                
                if (!dm.esModoActualManoLibre()) {
                    dibujarForma();
                }
                
                guardado = false;
            }
        });
        
        setBackground(Color.WHITE);
    }
    
    private void inicializarTrazo(Graphics2D g2d) {
        g2d.setColor(dm.getModoActual() != ModoCursor.BORRADOR ? dm.getColorActual() : Color.WHITE);
        g2d.setStroke(new BasicStroke(dm.getModoActual() != ModoCursor.BORRADOR ? 5 : 10,
                      BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }
    
    private void dibujar() {
        Graphics2D g2d = (Graphics2D) dibujo.getGraphics();
        
        inicializarTrazo(g2d);

        g2d.drawLine(x1, y1, x2, y2);

        x1 = x2;
        y1 = y2;
        
        g2d.dispose();
        repaint();
    }
    
    private void dibujarForma() {
        Graphics2D g2d = (Graphics2D) dibujo.getGraphics();
        
        inicializarTrazo(g2d);
        
        switch (dm.getModoActual()) {
            case LINEA:
                g2d.drawLine(x1, y1, x2, y2);
                break;
            case CIRCULO:
                if (x1 <= x2 && y1 <= y2) {
                    g2d.drawOval(x1, y1, x2 - x1, y2 - y1);
                }
                else if (x1 > x2 && y1 > y2) {
                    g2d.drawOval(x2, y2, x1 - x2, y1 - y2);
                }
                else if (x1 > x2) {
                    g2d.drawOval(x2, y1, x1 - x2, y2 - y1);
                }
                else if (y1 > y2) {
                    g2d.drawOval(x1, y2, x2 - x1, y1 - y2);
                }

                break;
            case CUADRADO:
                if (x1 <= x2 && y1 <= y2) {
                    g2d.drawRect(x1, y1, x2 - x1, y2 - y1);
                }
                else if (x1 > x2 && y1 > y2) {
                    g2d.drawRect(x2, y2, x1 - x2, y1 - y2);
                }
                else if (x1 > x2) {
                    g2d.drawRect(x2, y1, x1 - x2, y2 - y1);
                }
                else if (y1 > y2) {
                    g2d.drawRect(x1, y2, x2 - x1, y1 - y2);
                }

                break;
        }
        repaint();
    }
    
    
    
    private void registrarDibujo() {
        historialActual++;
        historial.set(historialActual, dm.copiarDibujo(dibujo));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (dibujo == null) {
            dibujo = new BufferedImage(dm.getWidth(), dm.getHeight(), BufferedImage.TYPE_INT_RGB);
            g = dibujo.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, dm.getWidth(), dm.getHeight());
            historial.set(0, dm.copiarDibujo(dibujo));
        }

        if (imagenHistorial != null) {
            super.paintComponent(g);
            dibujo = dm.copiarDibujo(imagenHistorial);
            imagenHistorial = null;
        }
        
        g.drawImage(dibujo, 0, 0, null);
        
    }
    
    public void deshacer() {
        if (historialActual > 0) {
            imagenHistorial = dm.copiarDibujo(historial.get(historialActual));
            --historialActual;
            repaint();
        }
    }
    
    public void rehacer() {
        if (historialActual < historial.size()) {
            imagenHistorial = dm.copiarDibujo(historial.get(historialActual));
            ++historialActual;
            repaint();
        }
    }
    
    public void crearNuevoDibujo() {
        guardado = false;
        dibujo = null;
        repaint();
    }
    
    public void guardar(String s) {
        dm.guardar(dibujo, s);
        guardado = true;
    }
    
    public void cargar(int index) {
        dibujo = dm.copiarDibujo(dm.cargar(index));
        repaint();
    }
    
    public void sobreescribir(int index) {
        dm.sobreescribir(index, dibujo);
    }
    
    public void setColorActual(Color color) {
        dm.setColorActual(color);
    }
    
    public void setModoActual(ModoCursor modo) {
        dm.setModoActual(modo);
    }
    
    public boolean getGuardado() {
        return guardado;
    }

    public int getIndexActual() {
        return dm.getIndexActual();
    }

    public void setIndexActual(int i) {
        dm.setIndexActual(i);
    }
    
    public boolean consultarNombres(String s) {
        return dm.consultarNombres(s);
    }
    
    public int obtenerIndexNombres(String s) {
        return dm.obtenerIndexNombres(s);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
