/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programabueno;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public class Principal extends JFrame{
    JButton b1;
    Image aqua;
    
    public Principal(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    setSize(400,300);
    setLayout(null);
    setLocationRelativeTo(null);
    setResizable(false);
        
    b1 = new JButton("Animacion");
    b1.setBounds(200, 50, 100, 40);
    
    b1.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
        Ventana v = new Ventana();
        }
    });
        this.setVisible(true);
        
        this.add(b1);
    }
    
}
