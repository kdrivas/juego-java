/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FACILIDADES;

import Model.Artefacto;
import Model.Entidad;
import Model.Saco;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author alulab14
 */
public class Aliado extends Entidad {

    private Saco mochila;
    private ArrayList<String> consejos;
    private ArrayList<Integer> nivelconsejos;

    public Aliado() {
        super();
        mochila = new Saco();
        consejos = new ArrayList<String>();
        nivelconsejos = new ArrayList<Integer>();
        caracterImp = 'C';
        setNombre("Hugo");

    }

    /**
     * @return the mochila
     */
    public Saco getMochila() {
        return mochila;
    }

    /**
     * @param mochila the mochila to set
     */
    public void setMochila(Saco mochila) {
        this.mochila = mochila;
    }

    public int mostrarMochila() {
        Iterator<Artefacto> it = mochila.getListaDeArtefactos().iterator();
        int indice = 0;

        if (!mochila.getListaDeArtefactos().isEmpty()) {
            System.out.println("---------------------MOCHILA----------------");
            while (it.hasNext()) {
                indice++;
                Artefacto obj = it.next();
                System.out.println(indice + ").- " + obj.getNombre() + " " + obj.getTipo());
            }
            System.out.println("--------------------------------------------");
            return 1;
        } else {
            System.out.println("---------------------MOCHILA----------------");
            System.out.println("Mochila vacia");
            System.out.println("--------------------------------------------");
            return 0;
        }

    }

    /**
     * @return the consejos
     */
    public ArrayList<String> getConsejos() {
        return consejos;
    }

    /**
     * @param consejos the consejos to set
     */
    public void setConsejos(ArrayList<String> consejos) {
        this.consejos = consejos;
    }

    /**
     * @return the nivelconsejos
     */
    public ArrayList<Integer> getNivelconsejos() {
        return nivelconsejos;
    }

    /**
     * @param nivelconsejos the nivelconsejos to set
     */
    public void setNivelconsejos(ArrayList<Integer> nivelconsejos) {
        this.nivelconsejos = nivelconsejos;
    }

}
