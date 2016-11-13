/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author us
 */
public class ImageLoader {
    
    public static BufferedImage loadImage(String path) throws IOException{
        return ImageIO.read(new File(path));
    }
   
}
