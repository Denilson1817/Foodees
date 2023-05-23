/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programabueno;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class Ventana extends JFrame{
    public static boolean detener = true;
    MiPanel pa = new MiPanel();
    Productor p = new Productor(pa);
    Consumidor c = new Consumidor(pa);
    public Ventana () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setSize(900,600);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
        inComponents();
        }
        
        public void inComponents(){
        	//panel componentes
		JPanel panel1 = new JPanel();
		panel1.setBounds(780,0,220,600);
		panel1.setLayout(null);
		panel1.setVisible(true);
		panel1.setBackground(Color.white);
	
                
		//panel animacion
		pa = new MiPanel();
		JPanel panel2 = new JPanel();
		panel2.setBounds(0,0,1000,600);
		panel2.setLayout(new BorderLayout());
		panel2.setBackground(Color.gray);
		panel2.setVisible(true);
		
                
                JButton jbotonComenzar= new JButton("Cocinar");
		jbotonComenzar.addActionListener(new Gestion(this,pa));
		jbotonComenzar.setEnabled(true);
                
                jbotonComenzar.setBounds(800,140,90,30);
		//jbotonAñadir.setBounds(60,100,90,30);
		
                pa.add(jbotonComenzar);
		//panel1.add(jbotonAñadir);
		
                
   
                
                panel2.add(pa);
		
		//this.getContentPane().add(panel1);
		this.getContentPane().add(panel2,BorderLayout.CENTER);
                
                this.setVisible(true);
	
                

    }
}
    
        

