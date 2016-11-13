/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Model.Arma;
import Model.Armadura;
import Model.Artefacto;
import Model.Enemigo;
import Model.Pocion;
import java.util.ArrayList;

/**
 *
 * @author alulab14
 */
public class GestorArtefactos {

    private ArrayList<Artefacto>objetos;

    public GestorArtefactos() {
        objetos = new ArrayList<>();
        
    }

    /**
     * @return the objetos
     */
    public ArrayList<Artefacto> getObjetos() {
        return objetos;
    }

    /**
     * @param objetos the objetos to set
     */
    public void setObjetos(ArrayList<Artefacto> objetos) {
        this.objetos = objetos;
    }

   

}
