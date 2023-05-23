/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.Control;


import baseDatos.view.panel1;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Oyente implements ActionListener{
panel1 pa1;	

public Oyente(panel1 pa) {
this.pa1=pa;	
}


@Override
public void actionPerformed(ActionEvent e) {
Conexion.con(pa1.tx1.getText(), pa1.tx2.getText());
pa1.tx1.setText(null);
pa1.tx2.setText(null);	
}
}
