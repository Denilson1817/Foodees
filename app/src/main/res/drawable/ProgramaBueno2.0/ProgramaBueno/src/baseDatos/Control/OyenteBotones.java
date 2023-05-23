/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.Control;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import baseDatos.view.panel2;

public class OyenteBotones implements ActionListener{

panel2 pa2;	
public OyenteBotones(panel2 pa) {
	this.pa2=pa;
}

@Override
public void actionPerformed(ActionEvent e) {
if(e.getActionCommand()=="CONSULTAR") {
String ingrediente=(String) pa2.Combo1.getSelectedItem();
String tamanio= (String) pa2.Combo2.getSelectedItem();
Conexion.Consulta(ingrediente, tamanio);	
}else if(e.getActionCommand()=="ELIMINAR"){
String ingrediente=	(String) pa2.Combo1.getSelectedItem();
String tamanio= (String) pa2.Combo2.getSelectedItem();
Conexion.Eliminar(ingrediente, tamanio);
}else if(e.getActionCommand()=="EDITAR"){
String ingrediente=	(String) pa2.Combo1.getSelectedItem();
String tamanio= (String) pa2.Combo2.getSelectedItem();

String ingredientenew=pa2.tx1.getText();
String tamanionew=pa2.tx2.getText();
float prenew=Float.parseFloat(pa2.tx3.getText());
if(ingredientenew=="" || tamanionew==""||prenew<101) {
	JOptionPane.showMessageDialog(null, "Error en algun dato");
}else {
Conexion.Actualizar(ingrediente, tamanio, ingredientenew, tamanionew,prenew);	
}


}else if(e.getActionCommand()=="AGREGAR") {
float pre=Float.parseFloat(pa2.tx3.getText());	
Conexion.Agregar(pa2.tx1.getText(),pa2.tx2.getText(),pre);	
}
	

}
}
