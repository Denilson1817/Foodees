/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.Control;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;


import baseDatos.view.panel2;


public class Conexion {
	
	
	panel2 pa2;
	
	
	public void Npanel(panel2 pa ) {
	this.pa2=pa;
	}
	

	public static void con(String ingr, String tam) {
		
	String ingrediente=ingr;
	String tamanio=tam;
	
	Connection c1=ConexionSingleton.getInstance();
	
	try {
	Statement st;	
	ResultSet rs;
	st=(Statement) c1.createStatement();
	
	String consulta ="select ingrediente, tamanio from pizza where ingrediente="+"'"+ingrediente+"'"+"and tamanio="+"'"+tamanio+"'";
	
	rs=st.executeQuery(consulta);
	
	if (rs.next()) {
		JOptionPane.showMessageDialog(null, "PUEDES HACER USO DEL CRUD");
		panel2.habilitar();
	}else {
		
		JOptionPane.showMessageDialog(null, "INGREDIENTE O TAMA�O INCORRECTOS");
	}
		rs.close();
	}catch(SQLException e) {
		System.out.println(e);
	}
		
	}
	
	
	public static void llenarBox(JComboBox combo, JComboBox combo2){
	Connection con=ConexionSingleton.getInstance();
	
	try {
	Statement st;
	ResultSet rs;
	st=(Statement) con.createStatement();
	String consulta2="select ingrediente, tamanio from pizza";
	rs=st.executeQuery(consulta2);
	while(rs.next()){
        combo.addItem(rs.getString("ingrediente"));
        //combo.addItem(rs.getString("ingrediente")+"-----"+rs.getString("tamanio"));
        combo2.addItem(rs.getString("tamanio"));
        }
	rs.close();
	}catch(SQLException e) {
		System.out.println(e);
	}
	
	}
	
	
	
public static void Consulta(String pizza,String tam) {
		Connection con=ConexionSingleton.getInstance();
		
try {
	Statement st;
	ResultSet rs;
	st=(Statement) con.createStatement();
	String consulta="select * from pizza where ingrediente='"+pizza+"'"+" and tamanio='"+tam+"'";
	rs=st.executeQuery(consulta);
	if(rs==null) {
	JOptionPane.showMessageDialog(null, "No existe este registro");
	}else {
		while(rs.next()) {
			panel2.tx1.setText(rs.getString("ingrediente"));
			panel2.tx2.setText(rs.getString("tamanio"));
			panel2.tx3.setText(rs.getString("precio"));
		} 
	}
	
	
rs.close();
}catch(SQLException e) {
	JOptionPane.showMessageDialog(null, "No existe este registro");
	System.out.println(e);
}
		
}
	
	

public static void Agregar(String ingre, String tam, float preci){
	Connection con=ConexionSingleton.getInstance();

	try {
		Statement st;
		ResultSet rs;
		st=(Statement) con.createStatement();
		String consulta="insert into pizza values('"+ingre+"','"+tam+"',"+preci+")";	 
		   if(st.executeUpdate(consulta)==1){
	            JOptionPane.showMessageDialog(null, "Registro dado de alta");
	            
	            panel2.tx1.setText(null);
	            panel2.tx2.setText(null);
	            panel2.tx3.setText(null);
	            
	            panel2.Combo1.removeAllItems();
	    		panel2.Combo2.removeAllItems();
	           llenarBox(panel2.Combo1, panel2.Combo2);
	           
		   }
		     
	}catch(SQLException e) {
		System.out.println(e);
		
	}
	
	
}
	
	
	
	public static void Eliminar(String pizza, String tam) {
		Connection con=ConexionSingleton.getInstance();

		try {
			Statement st;
			ResultSet rs;
			st=(Statement) con.createStatement();
			String consulta="delete from pizza where ingrediente='"+pizza+"'"+" and tamanio='"+tam+"'";	 
		
			   if(st.executeUpdate(consulta)==1){
		            JOptionPane.showMessageDialog(null, "Registro Eliminado");
		            panel2.Combo1.removeItem(pizza);
		            panel2.Combo2.removeItem(tam);
		            }else {
		            	 JOptionPane.showMessageDialog(null, "REGISTRO INEXISTENTE");	
		            }
			     
		}catch(SQLException e) {
			System.out.println(e);
			
		}
	}
	
	
	public static void Actualizar(String oldpizza, String oldtam, String newpizza, String newtam, float newpre) {
	Connection con=ConexionSingleton.getInstance();

	
	try {
	Statement st;
	ResultSet rs;
	st=(Statement) con.createStatement();
	String consulta="update pizza set ingrediente='"+newpizza+"',"+"tamanio='"+newtam+"',"+"precio="+newpre+
			" where ingrediente='"+oldpizza+"' and tamanio='"+oldtam+"'";
	if(st.executeUpdate(consulta)==1) {
		panel2.Combo1.removeAllItems();
		panel2.Combo2.removeAllItems();
		Conexion.llenarBox(panel2.Combo1, panel2.Combo2);
		//Mipanel2.tx1.
		JOptionPane.showMessageDialog(null, "Actualizacion Realizada");	
	}
	}catch(SQLException e) {
		System.out.println(e);;
	}
	}
	
	
	
	
public static Vector informacionVector(String var) {
		Connection con=ConexionSingleton.getInstance();
		Vector v1= new Vector();
		try{
			
		    Statement stm = con.createStatement();
		    ResultSet r = stm.executeQuery("select * from pizza where ingrediente='"+var+"'");

		    while(r.next()){
		    Vector personas = new Vector();
		    personas.addElement(r.getString("ingrediente"));
		    personas.addElement(r.getString("tamanio"));
		    personas.addElement(r.getString("precio"));
		    v1.add(personas);
		    }
		    r.close();
		    }catch(Exception e){
		    e.printStackTrace();
		    }
		    return v1;
		}

public static Vector informacionVectortam(String var) {
	Connection con=ConexionSingleton.getInstance();
	Vector v1= new Vector();
	try{
		
	    Statement stm = con.createStatement();
	    ResultSet r = stm.executeQuery("select * from pizza where tamanio='"+var+"'");

	    while(r.next()){
	    Vector personas = new Vector();
	    personas.addElement(r.getString("ingrediente"));
	    personas.addElement(r.getString("tamanio"));
	    personas.addElement(r.getString("precio"));
	    v1.add(personas);
	    }
	    r.close();
	    }catch(Exception e){
	    e.printStackTrace();
	    }
	    return v1;
	}
	

public static Vector titulosVector() {
		Vector v2=new Vector();
		v2.addElement("INGREDIENTE");
		v2.addElement("TAMANIO");
		v2.addElement("precio");
		return v2;	
		}
	
	
}