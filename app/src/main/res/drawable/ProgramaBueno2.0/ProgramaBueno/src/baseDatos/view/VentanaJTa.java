/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.view;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaJTa extends JFrame{

public static JTable jt2;	
	
public VentanaJTa(){
	super("TABLA CONSULTA");
	this.setBounds(200,300,300, 400);
	jt2 = new JTable();
	JScrollPane js = new JScrollPane(jt2);
	this.add(js);
	//this.setLocationRelativeTo(null);
	this.setVisible(true);
	//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
	
}
