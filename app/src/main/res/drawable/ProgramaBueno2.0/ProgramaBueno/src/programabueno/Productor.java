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
import static programabueno.MiPanel.x2;
import static programabueno.MiPanel.x1;
import static programabueno.MiPanel.x3;
import static programabueno.Ventana.detener;

/**
 *
 * @author Dell
 */
public class Productor extends Thread{
    MiPanel xp;
    public static boolean productor;
    
    public Productor(MiPanel xp){
    this.xp = xp;
    }
    
    public void producir(){
    synchronized(look){
        while(detener){
                        xp.moverPlato1X();
			xp.moverPlato2X();
			xp.moverPlato3X();
			xp.moverPlato4X();
			xp.repaint();
			try {
				Thread.sleep(8);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    look.notifyAll();
    
        try {
            look.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    /*
    @Override
    public void run(){
    
    while(detener){
                        xp.moverPlato1X();
			xp.moverPlato2X();
			xp.moverPlato3X();
			xp.moverPlato4X();
			xp.repaint();
			try {
				Thread.sleep(8);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	
        }
    }*/
}
