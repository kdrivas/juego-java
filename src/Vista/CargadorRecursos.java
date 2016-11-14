/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Carlos
 */
public class CargadorRecursos {
    public static BufferedImage cargarImagen(String ruta){
        Image imagen = null;
        
        try{
            imagen = ImageIO.read(new File(ruta));
        }catch (IOException e){
            e.printStackTrace();
        }
        
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
        BufferedImage imagenCompatible = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null));
        
        Graphics g = imagenCompatible.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();
        
        return imagenCompatible;
    }
    
    public static BufferedImage cargarImagenTransparente(String ruta){
        Image imagen = null;
        
        try{
            imagen = ImageIO.read(new File(ruta));
        }catch (IOException e){
            e.printStackTrace();
        }
        
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
        BufferedImage imagenCompatible = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.TRANSLUCENT);
        
        Graphics g = imagenCompatible.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();
        
        return imagenCompatible;
    }
}
