/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Rectangle;

/**
 *
 * @author Carlos
 */
public class Tile {
    private Sprite sprite;
    private int id;
    private boolean solido;
    
    public Tile(Sprite sprite, int id){
        this.sprite = sprite;
        this.id = id;
        this.solido = false;
    }
    
    public Tile(Sprite sprite, int id, boolean solido){
        this.sprite = sprite;
        this.id = id;
        this.solido = solido;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSolido() {
        return solido;
    }

    public void setSolido(boolean solido) {
        this.solido = solido;
    }
    
    public Rectangle obtenerLimites(int x, int y){
        return new Rectangle(x, y, sprite.getAncho(), sprite.getAlto());
    }
}
