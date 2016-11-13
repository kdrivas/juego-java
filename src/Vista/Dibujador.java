/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Model.Laberinto;
import Model.Celda;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author alulab14
 */
public class Dibujador {

    private int A;
    private int B;

    public Dibujador(int x, int y) {
        this.A = x;
        this.B = y;

    }

    public void dibujar(Laberinto lab) {
        // a y b distancia hacia los lados y distancia hacia arriba y abajo
        int ancho = 2 * getA() + 1;
        int largo = 2 * getB() + 1;

        //hallamos posicion del avatar 
        //falta modificar las funciones hallarPos para que realmente devuelvan pos
        int pos_x = lab.hallarPosXAvatar();
        int pos_y = lab.hallarPosYAvatar();

        //calculamos las coordenadas limite que se imprimiran del mapa
        int x_min = pos_x - A;
        int x_max = pos_x + A;
        int y_min = pos_y - B;
        int y_max = pos_y + B;

        //Verificar vision minima y maxima
        if(x_min < 0)
            x_min = 0;
        if(y_min < 0)
            y_min = 0;
        if(x_max >= lab.getM())
            x_max = lab.getM()-1;
        if(y_max >= lab.getN())
            y_max = lab.getN()-1;
        
        
        //loop de impresion
        Celda celda;
        //imprimo Marco
        for(int i = 0; i <= 2*A + 2; i++){
            System.out.print('-');
        }
        System.out.println();
        
        for(int i = y_min; i <= y_max; i++) {
            //imprimimos linea por linea
            System.out.print('|');
            for (int j = x_min; j <= x_max; j++) {
                celda = lab.getCeldaLaberinto(j, i);
                //si la celda esta afuera del laberinto
                
                if (celda.getEstado().equals("AFUERA")) {
                    System.out.print(' ');
                    //si la celda es una pared
                } else if (celda.getEstado().equals("PARED")) {
                    System.out.print('#');
                    //si la celda esta adentro del laberinto
                } else if (celda.getEstado().equals("ANTERIOR") || celda.getEstado().equals("SIGUIENTE") || celda.getEstado().equals("ADENTRO")) {
                    //celda vacia
                    if ((celda.getObjArtefacto() == null) && (celda.getObjEntidad() == null)) {
                        if(celda.getEstado().equals("SIGUIENTE")){
                            System.out.print('+');
                        } else if(celda.getEstado().equals("ANTERIOR")){
                            System.out.print('-');
                        }else{
                            System.out.print(' ');
                        }
                        //celda con avatar o enemigo (se imprime entidad sin importar si hay artefacto)
                    } else if (celda.getObjEntidad() != null) {
                        System.out.print(celda.getObjEntidad().getCaracterImp());
                        //celda sin avatar y con artefacto
                    } else {
                        System.out.print(celda.getObjArtefacto().getCaracterImp());
                    }
                }
            }
            //cambiamos de linea
            System.out.println();
        }
        for(int i = 0; i <= 2*A; i++){
            System.out.print('-');
        }
        System.out.println();
    }

    /**
     * @return the A
     */
    public int getA() {
        return A;
    }

    /**
     * @param A the A to set
     */
    public void setA(int A) {
        this.A = A;
    }

    /**
     * @return the B
     */
    public int getB() {
        return B;
    }

    /**
     * @param B the B to set
     */
    public void setB(int B) {
        this.B = B;
    }
    
    
}
