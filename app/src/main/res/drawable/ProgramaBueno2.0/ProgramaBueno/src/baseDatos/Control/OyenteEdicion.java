/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.Control;

import baseDatos.view.panel2;
import baseDatos.view.VentanaJTable;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
public class OyenteEdicion implements ActionListener{

panel2 pa2;	

public OyenteEdicion(panel2 pa) {
this.pa2=pa;
}
	
@Override	
public void actionPerformed(ActionEvent e) {
if(e.getActionCommand()=="HABILITAR") {
pa2.tx1.setEditable(true);
pa2.tx2.setEditable(true);
pa2.tx3.setEditable(true);	
}else if(e.getActionCommand()=="INHABILITAR"){
	pa2.tx1.setText(null);
	pa2.tx2.setText(null);
	pa2.tx3.setText(null);
	pa2.tx1.setEditable(false);
	pa2.tx2.setEditable(false);
	pa2.tx3.setEditable(false);
}else if(e.getActionCommand()=="CONSULTA SABOR") {
	String txt=(String) panel2.Combo1.getSelectedItem();
	DefaultTableModel dt = new DefaultTableModel(Conexion.informacionVector(txt),Conexion.titulosVector());
	VentanaJTable tablacons=new VentanaJTable();
	tablacons.jt2.setModel(dt);	
}else if(e.getActionCommand()=="CONSULTA TAMA�O") {
	String txt=(String) panel2.Combo2.getSelectedItem();
	DefaultTableModel dt = new DefaultTableModel(Conexion.informacionVectortam(txt),Conexion.titulosVector());
	VentanaJTable tablacons=new VentanaJTable();
	tablacons.jt2.setModel(dt);
}
	

}
}
