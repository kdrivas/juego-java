/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;


/**
 *
 * @author alulab14
 */
public class Saco {

    private int espacioTotal;
    private int espacioActual;
    private ArrayList<Artefacto> listaDeArtefactos;

    public Saco() {
        espacioTotal = 20;
        espacioActual = 20;
        listaDeArtefactos = new ArrayList<Artefacto>();
    }

    public int getEspacioTotal() {
        return espacioTotal;
    }

    public void setEspacioTotal(int espacioTotal) {
        this.espacioTotal = espacioTotal;
    }

    public int getEspacioActual() {
        return espacioActual;
    }

    public void setEspacioActual(int espacioActual) {
        this.espacioActual = espacioActual;
    }

    public ArrayList<Artefacto> getListaDeArtefactos() {
        return listaDeArtefactos;
    }

    public void setListaDeArtefactos(ArrayList<Artefacto> ListaDeArtefactos) {
        this.listaDeArtefactos = ListaDeArtefactos;
    }

    public int agregarArtefacto(Artefacto objeto) {
        if (objeto.getEspacio() <= espacioActual) {
            listaDeArtefactos.add(objeto);
            espacioActual = espacioActual - objeto.getEspacio();
            return 1;
        } else {
            System.out.println("No hay espacio suficiente");
            return 0;
        }
    }

    public Artefacto seleccionarArtefacto(int indice) {
        return listaDeArtefactos.get(indice);
    }

    public int botarArtefacto(Artefacto objeto) {
        int flag = 0;
        int indice = 0;
        while (flag == 0 && indice < listaDeArtefactos.size()) {
            if (listaDeArtefactos.get(indice).getNombre().equals(objeto.getNombre())) {
                flag = 1;
                listaDeArtefactos.remove(indice);
            }
            indice++;
        }
        if (flag == 1) {
            return 1;
        } else {
            System.out.println("El objeto que desea botar no se encuentra en la mochila");
            return 0;
        }
    }
}
