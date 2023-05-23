/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.Control;


import java.sql.Connection;
import java.sql.DriverManager;



public class ConexionSingleton {

private static Connection con=null;	


public ConexionSingleton() {
	
}


public static Connection getInstance() {
	if(con==null) {
		try {
		
		String URL="jdbc:mysql://localhost:3306/pizzeria";	
		String USER="root";
		String password="aaron2018";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection(URL,USER,password);
		System.out.println("Conexion principal exitosa");
				
		}catch(Exception e) {
			System.out.println(e);
		}
			
}	
	return con;
}	
}
