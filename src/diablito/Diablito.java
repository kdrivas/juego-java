/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diablito;

import Controlador.GestorArtefactos;
import FACILIDADES.Aliado;
import Controlador.GestorLaberinto;
import java.util.Scanner;
import Model.*;
import Vista.*;
import Vista.Menu;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 */
public class Diablito {

    /**
     * @param args the command line arguments
     */
    //Setteo cantidad de niveles, tambien debe ser modificado en gestor laberinto

    public static void main(String[] args) throws IOException {

        Juego juego= new Juego();
        juego.jugar();
    }

}