/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programabueno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import static programabueno.Ventana.detener;

/**
 *
 * @author Dell
 */
public class Gestion implements ActionListener{
    MiPanel xp;
    Ventana v;
    public Gestion(Ventana v, MiPanel xp){
    this.v = v;
    this.xp = xp;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    Thread rm = new Thread(xp);
    
    rm.start();
    
    
    }
    
}
