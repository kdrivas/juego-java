/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


import Controlador.GestorLaberinto;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.Timer;
import Model.*;
import static Vista.Juego.CANT_NIVELES;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author us
 */
public class Board extends JPanel implements KeyListener {

    private Map m;
    private Timer timer;
    private Laberinto lab;
    private int x;
    private int y;

    public Board(Juego juego, char mov) throws IOException {
        addKeyListener(this);
        setFocusable(true);
        m = new Map();
        x = 0;
        y = 0;
 
                   
   
//            boolean salir = false;
//            int nivelActual = 0;
//            Scanner sc = new Scanner(System.in);
//            GestorLaberinto glab = new GestorLaberinto();
//            Avatar pj = new Avatar();
//            Vector<Laberinto> niveles = new Vector<>();
//            ArrayList<Arma> armas = new ArrayList<>();
//            ArrayList<Armadura> armaduras = new ArrayList<>();
//            ArrayList<Pocion> pociones = new ArrayList<>();
//            //cargar los enemigos y objetos
//            ArrayList<Enemigo> enemigos = juego.cargarEnemigos("..\\src\\enemigos.txt");

            
            //juego.cargarArtefactos(armas, armaduras, pociones);


                // aqui ira menu de partidas proximamente(para cargar partida o comenzar una
                // Se crea los laberintos(niveles)
                int x = 0, y = 0;


                    
                    //settear enemigos en el mapa
//                    juego.agregarEnemigos(enemigos, niveles);
//                    juego.agregarArmas(armas, niveles);
//                    juego.agregarArmaduras(armaduras, niveles);
//                    juego.agregarPociones(pociones, niveles);

                    // se coloca inicio y fin en cada laberinto
                    // coloco enemigos y objetos en laberinto
                    // se crea al personaje
                    //pj = juego.crearAvatar(juego.agregarArmaduras(lstArmaduras, niveles)armas.get(0));

                    //ubica al personaje en el mapa del juego;
                    if(juego.isInicio())
                        juego.colocarAvatarInicio(juego.getNiveles().elementAt(juego.getNivelActual()), juego.getPj());
                    else{
                        juego.setInicio(false);
                    }
                    // coloca aliado  
                    //for (int i = 0; i < niveles.size(); i++) {
                    //Aliado al = new Aliado();
                    //escojo un aliado de la lista
                    //  Random rnaliado = new Random();
                    //int indicealiado = (int) (rnaliado.nextDouble() * aliados.size());
                    //al = aliados.get(indicealiado);
                    //colocarAliado(al, niveles, i);
                    //}
                
                // se muestra  mapa
                Dibujador dbj = new Dibujador(7, 7);

                char dir = ' ';

                //if(opcion == '1')
                
                //else
                //    meMovi = false;

                //while (!salir && !pj.muerto()) {   // empieza juego movimientos e interaccion del avatar
                    x = 0;
                    y = 0;
                    Celda celdaActual = new Celda();
                    Celda celdaSiguiente = new Celda();
                    Celda celdaFin;
                    repaint();
                    // en el nivel en el que estamos  hallamos posicion del avatar y realizamos movimiento
                    x = juego.getNiveles().elementAt(juego.getNivelActual()).hallarPosXAvatar();
                    y = juego.getNiveles().elementAt(juego.getNivelActual()).hallarPosYAvatar();
                    //AGREGAR CONDICION QUE SOLO SEA POR LOS COMANDOS DE  MOVIMIENTO
//                    if (inicio) {// para que solo se enpiecen a mover despues de comenzado el juego
//                        inicio = false;
//                    } else {
//                        juego.moverEnemigosLaberinto(juego.getNiveles().elementAt(juego.getNivelActual()));
//                        juego.moverAliadoLaberinto(juego.getNiveles().elementAt(juego.getNivelActual()));
//                    }
                    //Verifico si paso de nivel
//                    if (meMovi && niveles.elementAt(juego.getNivelActual()).getCeldaLaberinto(x, y).getEstado().equals("SIGUIENTE")) {
//                        
//                        if (nivelActual >= CANT_NIVELES) {
//                            //termine el juego
//                            fin = true;
//
//                            //Ganaste
//                            juego.getMenu().ganaste();
//                        
//                        } else {
//                            niveles.elementAt(nivelActual - 1).getCeldaLaberinto(x, y).setObjEntidad(null);
//                            //Se vuelve a calcular la nueva posicion del avatar en el nuevo nivel
//                            juego.colocarAvatarInicio(niveles.elementAt(nivelActual), pj);
//                            x = niveles.elementAt(nivelActual).hallarPosXAvatar();
//                            y = niveles.elementAt(nivelActual).hallarPosYAvatar();
//                        }
//                    } else if (meMovi && niveles.elementAt(nivelActual).getCeldaLaberinto(x, y).getEstado().equals("ANTERIOR")) {
//                        if (nivelActual != 0) {
//                            nivelActual--;
//                            niveles.elementAt(nivelActual + 1).getCeldaLaberinto(x, y).setObjEntidad(null);
//                            //Vuelvo al anterior laberinto
//                            juego.colocarAvatarFinal(niveles.elementAt(nivelActual), pj);
//                            x = niveles.elementAt(nivelActual).hallarPosXAvatar();
//                            y = niveles.elementAt(nivelActual).hallarPosYAvatar();
//                        }
//                    }

                    //Meter a un enemigo
                    //niveles.elementAt(0).getCeldaLaberinto(1, 2).setObjEntidad(enemigos.get(0));
                    //dibujo posicion actual                      
                    

                    //Informacion del avatar
                    

                    System.out.println("Controles:");
                    System.out.println("w:arriba  s:abajo   d:derecha   a:izquierda   ");
                    System.out.println("m:Acceder mochila  e:interactuar  g:guardar juego  z:salir   ");// ya no hay opcion atacar para la segunda modificacion

                    celdaActual = juego.getNiveles().elementAt(juego.getNivelActual()).getCeldaLaberinto(x, y);
                    lab = juego.getNiveles().elementAt(0);
                    this.x = x;
                    this.y = y;
                    repaint();
                    
                    
                    switch (mov) {//detectamos que quiere hacer el jugador
                        case 'd':// se mueve a la derecha;
                            dir = mov;
                            //meMovi = true;
                            juego.mover(juego.getNiveles().elementAt(juego.getNivelActual()), juego.getNivelActual(), celdaActual, juego.getPj(), x + 1, y);
                            break;
                        case 's':
                            dir = mov;
                            //meMovi = true;
                            juego.mover(juego.getNiveles().elementAt(juego.getNivelActual()), juego.getNivelActual(), celdaActual, juego.getPj(), x, y + 1);
                            break;
                        case 'w':
                            dir = mov;
                            //meMovi = true;
                            juego.mover(juego.getNiveles().elementAt(juego.getNivelActual()), juego.getNivelActual(), celdaActual, juego.getPj(), x, y - 1);
                            break;
                        case 'a':
                            dir = mov;
                            //meMovi = true;
                            juego.mover(juego.getNiveles().elementAt(juego.getNivelActual()), juego.getNivelActual(), celdaActual, juego.getPj(), x - 1, y);
                            break;
                        case 'm'://muestra mochila
                            //juego.menuMochila(pj);
                            //meMovi = false;
                            break;
                        case 'e':
                            // INTERACTUAR
                            //meMovi = false;
                            String cmnd = JOptionPane.showInputDialog("Escribe la direccion: ");
                            JOptionPane.showMessageDialog(null, "El comando ingresado es: " + cmnd);
                            dir = cmnd.charAt(0);
                            juego.interactuar(dir, juego.getPj(), juego.getNiveles().elementAt(juego.getNivelActual()));

                            break;
                        case 'g':
                            try {
                                XStream xs = new XStream();

                                FileWriter fw = new FileWriter("juegoGuardado.txt");
                                fw.write(xs.toXML(juego.getNiveles()));
                                fw.close();
                            } catch (IOException e) {
                                System.out.println(e.toString());
                            }
                            break;
                        case 'z': // sale del juego (deberia guarda tb)      
                            //juego.get = true;
                            break;
                    }

                    
                
 
    

    }   
//
            
                    
    

    @Override
       public void keyReleased(KeyEvent e) {
   System.out.println("lasklhdjklashdjkashdjkashdjkas");
    
        
        if(e.getKeyCode()== KeyEvent.VK_D){
            System.out.println("lasklhdjklashdjkashdjkashdjkas");
       
   
        }
        else if(e.getKeyCode()== KeyEvent.VK_A){
     
           
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){
   
        
        }
        else if(e.getKeyCode()== KeyEvent.VK_W){
       
      
        }
    }
       
       @Override
       public void keyPressed(KeyEvent e){
           
       }
@Override
       public void keyTyped(KeyEvent e){
           
       }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int A = 5, B = 5;
        int ancho = 2 * A + 1;
        int largo = 2 * B + 1;
        int pos_x = lab.hallarPosXAvatar();
        int pos_y = lab.hallarPosYAvatar();
        int x_min = pos_x - A;
        int x_max = pos_x + A;
        int y_min = pos_y - B;
        int y_max = pos_y + B;
        if (x_min < 0) {
            x_min = 0;
        }
        if (y_min < 0) {
            y_min = 0;
        }
        if (x_max >= lab.getM()) {
            x_max = lab.getM() - 1;
        }
        if (y_max >= lab.getN()) {
            y_max = lab.getN() - 1;
        }
        Celda celda;
        for (int y = y_min; y < y_max; y++) {
            for (int x = x_min; x < x_max; x++) {
                celda = lab.getCeldaLaberinto(x, y);
                if (celda.getEstado().equals("AFUERA")) {
                    //si la celda es una pared
                } else if (celda.getEstado().equals("PARED")) {
                    g.drawImage(m.getWall(), x * 48, y * 64, this);
                    //si la celda esta adentro del laberinto
                } else if (celda.getEstado().equals("ANTERIOR") || celda.getEstado().equals("SIGUIENTE") || celda.getEstado().equals("ADENTRO")) {
                    //celda vacia
                    if ((celda.getObjArtefacto() == null) && (celda.getObjEntidad() == null)) {
                        if (celda.getEstado().equals("SIGUIENTE")) {
                            g.drawImage(m.getNext(), x * 48, y * 64, this);
                        } else if (celda.getEstado().equals("ANTERIOR")) {
                            g.drawImage(m.getBefore(), x * 48, y * 64, this);
                        } else {
                            g.drawImage(m.getGrass(), x * 48, y * 64, this);
                        }
                        //celda con avatar o enemigo (se imprime entidad sin importar si hay artefacto)
                    } else if (celda.getObjEntidad() != null) {
                        if (celda.getObjEntidad() instanceof Avatar) {
                            g.drawImage(m.getAvatar(), x * 48, y * 64, this);
                        } else {
                            g.drawImage(m.getEnemy(), x * 48, y * 64, this);
                        }
                        //celda sin avatar y con artefacto
                    } else if (celda.getObjArtefacto() instanceof Arma) {
                        g.drawImage(m.getWeapon(), x * 48, y * 64, this);
                    } else if (celda.getObjArtefacto() instanceof Armadura) {
                        g.drawImage(m.getArmor(), x * 48, y * 64, this);
                    } else if (celda.getObjArtefacto() instanceof Pocion) {
                        g.drawImage(m.getPotion(), x * 48, y * 64, this);
                    }
                }
            }
        }
    }
}
