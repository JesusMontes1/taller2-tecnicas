/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodibujo.Modelo;

import com.mycompany.proyectodibujo.PanelDibujo;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class DibujosAction extends AbstractAction {
    private PanelDibujo pd;
    private int index = 0;
    
    public DibujosAction(PanelDibujo pd, int index) {
        this.pd = pd;
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!pd.getGuardado()) {
            int eleccion = JOptionPane.showConfirmDialog(null, "No ha guardado su dibujo, ¿continuar?", "Advertencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (eleccion == JOptionPane.YES_OPTION) {
                pd.cargar(index);
                pd.setIndexActual(index);
            }
        }
        else {
            pd.cargar(index);
            pd.setIndexActual(index);
        }
    }
}
