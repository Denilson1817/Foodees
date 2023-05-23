/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programabueno;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import static programabueno.Ventana.detener;

/**
 *
 * @author Dell
 */
public class MiPanel extends JPanel implements Runnable{
    public static Object look = new Object();
    Image fondo,plato,pacman,chef,mesa;
    int a=85, b=85;
public static int x1 = -730;
public static int x2 = -570;
public static int x3 = -410;
public static int x4 = -250;
public static int x5 = 800;
public static int pizza = 0;
    Ventana ven;
    BufferedImage b1;
    Productor p = new Productor(this);
    Consumidor c = new Consumidor(this);
    
    public MiPanel(Ventana ven) {
		this.ven = ven;
    }
    
    public MiPanel(){
        this.setBackground(Color.GREEN);
        this.setLayout(null);
         
        Toolkit herramienta = Toolkit.getDefaultToolkit();
        fondo = herramienta.getImage(getClass().getResource("/ima/resta.jpg"));
        plato = herramienta.getImage(getClass().getResource("/ima/pizza.jpg"));
        chef = herramienta.getImage(getClass().getResource("/ima/fantasma.jpg"));
        pacman = herramienta.getImage(getClass().getResource("/ima/pacman.jpg"));
        mesa = herramienta.getImage(getClass().getResource("/ima/mesa.png"));
    }
    
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(fondo, 0, 0, 900, 600, this);
		mesa(g2);
                
		if(pizza == 0){
                        //pizza 1
                         g2.drawImage(plato, x1, 230, a, b, this);
                         this.repaint();		
			//pizza 2;
			
			g2.drawImage(plato,x2,230,a,b,this);
			this.repaint();
                        //pizza 3
			g2.drawImage(plato,x3,230,a,b,this);
			this.repaint();
		
			//pizza 4
			
			g2.drawImage(plato,x4,230,a,b,this);
			this.repaint();
                }
                actores(g2);
    }

	public void actores(Graphics g) {
		//Productor
		g.drawImage(chef,30,70,100,100,this);
		//Consumidor
		g.drawImage(pacman,x5,225,100,100,this);
	}
	public void mesa(Graphics g) {
		
		g.drawImage(mesa,130,200,650,300, this);
		}
        
        int velX, velY, tam;
	public void moverPlato1X(){
		if (x1 + velX < -300) velX = -velX;
		x1 = x1 + 1;
	}
	public void moverPlato2X(){
		if (x2 + velX < -300) velX = -velX;
		x2 = x2 + 1;
	}
	public void moverPlato3X(){
		if (x3 + velX < -300) velX = -velX;
		x3 = x3 + 1;
	}
	@SuppressWarnings("static-access")
	public void moverPlato4X(){
		if (x4 + velX < -300) velX = - velX;
		x4 = x4 + 1;
		if(x1 == 800) {
			ven.detener = false;
                        x4=x5;
		}
	}
        
        public void moverComer(){
             x1 = -730;
             x2 = -570;
             x3 = -410;
             x4 = -250;
             p.producir();
        }
        
         @Override
    public void run() {
    while(true){
    p.producir();
    c.comer();
    }
    }
        

    
}
