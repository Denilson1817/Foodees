/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.view;


import javax.swing.JFrame;
import java.awt.GridLayout;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
public class Principal1 extends JFrame {
panel1 pa1;
panel2 pa2;
public Principal1() {
super("Busca una pizza");
this.setLayout(new GridLayout(1,1));
this.setBounds(200,300,900, 500);
pa1=new panel1(this);
this.add(pa1);
pa2=new panel2(this);
this.add(pa2);
this.setLocationRelativeTo(null);
this.setVisible(true);
this.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
	
}
