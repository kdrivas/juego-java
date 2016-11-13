/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.ImageIcon;
/**
 *
 * @author us
 */
public class Map {
    private Scanner m;
    private String Map[] = new String[14];
    private Image grass,wall,avatar,enemy,next,before,potion,weapon,armor;
    private BufferedImage test;
    private SpriteSheet sheet;
    public Map() throws FileNotFoundException, IOException {
        test = ImageLoader.loadImage("../src/imagenes/todos.png");
        sheet = new SpriteSheet(test);
        grass = sheet.crop(48, 0, 48, 64);
        wall = sheet.crop(0, 0, 48, 64);
        avatar = sheet.crop(192, 0, 48, 64);
        enemy = sheet.crop(240,0,48,64);
        next = sheet.crop(96, 0, 48, 64);
        before = sheet.crop(144, 0, 48, 64);
        weapon = sheet.crop(288, 0, 48, 64);
        armor = sheet.crop(336, 0, 48, 64);
        potion = sheet.crop(384,0,48,64);
        
        openFile();
        readFile();
        closeFile();
    }
    
    public String getMap(int x, int y) {
        String index = Map[y].substring(x,x+1);
        return index;
        
    }
    
    private void openFile() throws FileNotFoundException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //m = new Scanner(new FileReader("map.txt"));
    }

    private void readFile() throws FileNotFoundException, IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        while(m.hasNext()){
//            for(int i=0;i<14;i++){
//                Map[i] = m.next();
//            }
//        }
        BufferedReader br = new BufferedReader(new FileReader("../src/map.txt" ));
        String line;
        int i =0;
        while(i<14){
            Map[i]=br.readLine();
            i++;
        }
    }

    private void closeFile() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //m.close();
    }

    public Image getGrass() {
        return grass;
    }

    public Image getWall() {
        return wall;
    }
    
    public Image getAvatar(){
        return avatar;
    }
    
    public Image getEnemy(){
        return enemy;
    }
    
    public Image getNext(){
        return next;
    }
    
    public Image getBefore(){
        return before;
    }
    
    public Image getWeapon(){
        return weapon;
    }
    
    public Image getArmor(){
        return armor;
    }
    
    public Image getPotion(){
        return potion;
    }
    
}