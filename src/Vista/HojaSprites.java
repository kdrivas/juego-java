/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Carlos
 */
public class HojaSprites {
    
    
    ////////NO TE CONCENTRES EN ESTA CLASE SALVO QUE SEA PARA AÑADIR MAS SPRITES
    
    
    private static final int ANCHO_SPRITE = 48;
    private static final int ALTO_SPRITE = 64;
    
    private int ancho;
    private int alto;
    
    private BufferedImage imagen;
    private HashMap<String, Sprite> hashMap;
    
    public HojaSprites(String ruta, int ancho, int alto){
        BufferedImage imagenHoja;
        
        imagenHoja = CargadorRecursos.cargarImagen(ruta);
        
        ancho = imagenHoja.getWidth();
        alto = imagenHoja.getHeight();
        
        hashMap = new HashMap();
//        grass = sheet.crop(48, 0, 48, 64);
//        avatar = sheet.crop(192, 0, 48, 64);
//        enemy = sheet.crop(240,0,48,64);
//        next = sheet.crop(96, 0, 48, 64);
//        before = sheet.crop(144, 0, 48, 64);
//        weapon = sheet.crop(288, 0, 48, 64);
//        armor = sheet.crop(336, 0, 48, 64);
//        potion = sheet.crop(384,0,48,64);
        hashMap.put("PARED", new Sprite(imagenHoja.getSubimage(0, 0, 48, 64)));
        hashMap.put("ADENTRO", new Sprite(imagenHoja.getSubimage(48, 0, 48, 64)));
        hashMap.put("VACIO", new Sprite(imagenHoja.getSubimage(48, 0, 48, 64)));
        hashMap.put("ANTERIOR", new Sprite(imagenHoja.getSubimage(192, 0, 48, 64)));
        hashMap.put("SIGUIENTE", new Sprite(imagenHoja.getSubimage(192, 0, 48, 64)));
        hashMap.put("AVATAR", new Sprite(imagenHoja.getSubimage(192, 0, 48, 64)));
        hashMap.put("JUGADOR_IZQUIERDA", new Sprite(imagenHoja.getSubimage(192, 0, 48, 64)));
        hashMap.put("JUGADOR_DERECHA", new Sprite(imagenHoja.getSubimage(192, 0, 48, 64)));
        hashMap.put("JUGADOR_CAMINA0", new Sprite(imagenHoja.getSubimage(336, 0, 48, 64)));
        hashMap.put("JUGADOR_CAMINA1", new Sprite(imagenHoja.getSubimage(288, 0, 48, 64)));
    }
    
    public BufferedImage SpriteKey(String key){
        return hashMap.get(key).imagen;
    }
}
