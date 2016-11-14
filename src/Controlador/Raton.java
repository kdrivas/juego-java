/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.CargadorRecursos;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos
 */
public class Raton {
    public final Cursor cursor;
    
    public Raton(){
        Toolkit configuracion = Toolkit.getDefaultToolkit();
        BufferedImage icono = CargadorRecursos.cargarImagenTransparente("../src/imagenes/cursor.png");
    
        Point punta = new Point(0, 0);
        cursor = configuracion.createCustomCursor(icono, punta, "cursor");
    }
}
