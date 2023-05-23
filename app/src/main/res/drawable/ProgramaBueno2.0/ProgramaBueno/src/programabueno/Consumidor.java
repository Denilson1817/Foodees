/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programabueno;

import java.util.logging.Level;
import java.util.logging.Logger;
import static programabueno.MiPanel.look;
import static programabueno.MiPanel.pizza;
import static programabueno.MiPanel.x1;
import static programabueno.MiPanel.x4;
import static programabueno.MiPanel.x5;
import static programabueno.Ventana.detener;

/**
 *
 * @author Dell
 */
public class Consumidor extends Thread {
    
    MiPanel xp;
   
    public Consumidor(MiPanel xp){
    this.xp = xp;
    }
    
    public void comer(){
    synchronized(look){
    xp.moverComer();
    look.notifyAll();
        try {
            look.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    }
    
    

