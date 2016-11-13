/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author Carlos
 */
public class PanelInformacion extends JPanel implements ActionListener{
   
    private Timer timer;


    public PanelInformacion() throws IOException {
        timer = new Timer(25, this);
        timer.start();
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(10, 10, 10, 10);
         g.drawString("lalalalallala", 0, 0);
          g.drawString("oh nooo", 0, 0);
    }

}

    

