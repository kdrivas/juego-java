/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FACILIDADES;

/**
 *
 * @author alulab14
 */
import java.util.Comparator;

public class Consejo {
    private int puntos;
    private String mensaje;

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    public static Comparator<Consejo> puntosCs = new Comparator<Consejo>() {

	public int compare(Consejo c1, Consejo c2) {

	   int pt1 = c1.puntos;
	   int pt2 = c2.puntos;

	   /*For ascending order*/
	   return pt1-pt2;

	   /*For descending order*/
	   //rollno2-rollno1;
   }};
    
    public String toString() {
        return "[ puntos=" + puntos + ", consejo=" + mensaje + "]";
    }
    
}
