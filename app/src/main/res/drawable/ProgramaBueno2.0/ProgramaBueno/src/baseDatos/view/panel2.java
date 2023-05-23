/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.view;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import baseDatos.Control.Conexion;
import baseDatos.Control.OyenteBotones;
import baseDatos.Control.OyenteEdicion;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
public class panel2 extends JPanel{
public boolean visible2=false;	
Principal1 pa1;
public static JComboBox Combo1, Combo2;
static JButton boton1,boton2, boton3, boton4, boton5, boton6;
static JButton consulta, consulta2;

public static JTable tabla1;
JLabel l1;
JLabel l2;
JLabel l3;
public static JTextField tx1,tx2, tx3;
JPanel panelWest, panelCenter;

public panel2(Principal1 p){
this.pa1=p;
this.setBackground(Color.RED);
this.setLayout(new BorderLayout(10,10));
Componentes();
inhabilitarbotones();
}


public void Componentes() {
Combo1=new JComboBox();
Combo2=new JComboBox();
Conexion.llenarBox(Combo1, Combo2);
boton1=new JButton("CONSULTAR");
boton2=new JButton("ELIMINAR");
boton3=new JButton("AGREGAR");
boton4=new JButton("EDITAR");
boton5=new JButton("HABILITAR");
boton6=new JButton("INHABILITAR");
consulta =new JButton("CONSULTA SABOR");	
consulta2=new JButton("CONSULTA TAMA�O");
OyenteBotones botonesOY=new OyenteBotones(this);
boton1.addActionListener(botonesOY);
boton2.addActionListener(botonesOY);
boton3.addActionListener(botonesOY);
boton4.addActionListener(botonesOY);

panelWest= new JPanel();
panelWest.setLayout(new GridLayout(4,1));
panelWest.add(boton1);
panelWest.add(boton2);
panelWest.add(boton3);
panelWest.add(boton4);

l1=new JLabel("Ingrediente");
l2=new JLabel("Tama�o");
l3=new JLabel("Precio");
tx1=new JTextField(null);
tx2=new JTextField(null);
tx3=new JTextField(null);
tx1.setEditable(false);	
tx2.setEditable(false);
tx3.setEditable(false);
//BloquearTXT();

OyenteEdicion habilitar=new OyenteEdicion(this);
boton5.addActionListener(habilitar);
boton6.addActionListener(habilitar);
consulta.addActionListener(habilitar);
consulta2.addActionListener(habilitar);
panelCenter =new JPanel();
panelCenter.setBackground(Color.BLUE);
panelCenter.setLayout(new GridLayout(3,3));
panelCenter.add(l1);
panelCenter.add(l2);
panelCenter.add(l3);
panelCenter.add(tx1);
panelCenter.add(tx2);
panelCenter.add(tx3);
panelCenter.add(boton5);
panelCenter.add(boton6);

JPanel panelSouth=new JPanel(new GridLayout(1,2));
panelSouth.add(consulta);
panelSouth.add(consulta2);
//panel de JComboBox
JPanel panelCombo=new JPanel(new GridLayout(1,2));
panelCombo.add(Combo1);
panelCombo.add(Combo2);


this.add(panelWest,BorderLayout.WEST);
this.add(panelCombo,BorderLayout.NORTH);
this.add(panelCenter,BorderLayout.CENTER);
this.add(panelSouth,BorderLayout.SOUTH);


}

public void inhabilitarbotones() {
boton1.setEnabled(false);	
boton2.setEnabled(false);	
boton3.setEnabled(false);	
boton4.setEnabled(false);	
boton5.setEnabled(false);	
boton6.setEnabled(false);	
consulta.setEnabled(false);
consulta2.setEnabled(false);
Combo1.setEnabled(false);
Combo2.setEnabled(false);
}

public static void habilitar() {
boton1.setEnabled(true);	
boton2.setEnabled(true);	
boton3.setEnabled(true);	
boton4.setEnabled(true);	
boton5.setEnabled(true);	
boton6.setEnabled(true);	
consulta.setEnabled(true);
consulta2.setEnabled(true);
Combo1.setEnabled(true);
Combo2.setEnabled(true);
panel1.tx1.setEnabled(false);
panel1.tx2.setEnabled(false);
panel1.boton.setEnabled(false);
}


}
