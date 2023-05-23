/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import baseDatos.Control.Oyente;

import java.awt.GridLayout;
import java.awt.Font;


public class panel1 extends JPanel{
boolean visible=true;	
Principal1 p1;
JLabel l1;
JLabel l2;
public static JTextField tx1, tx2;
public static JButton boton;
public panel1(Principal1 p) {
this.p1=p;
this.setBackground(Color.BLUE);
this.setLayout(new GridLayout(3,2));
componentes();
this.setVisible(visible);
}

public void componentes() {
l1=new JLabel("Ingrediente de la pizza.");
Font auxFont=l1.getFont();
l1.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
l2=new JLabel("Tama�o de pizza.");
l2.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
tx1=new JTextField(null);
tx2=new JTextField(null);
boton= new JButton("Verificar Registro");
this.add(l1);
this.add(l2);
this.add(tx1);
this.add(tx2);
this.add(boton);
Oyente oy1=new Oyente(this);
boton.addActionListener(oy1);

}

}
