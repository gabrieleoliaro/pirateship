package org.vashonsd.pirateship.io;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI {
	
	public void Run() {
        //Create and set up the window.
        JFrame frame = new JFrame("Vashon PirateShip");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //JLabel emptyLabel = new JLabel("HELLO WORLD!!!!!!!!!!!!!!!!!!!!!!");
        //emptyLabel.setPreferredSize(new Dimension(500, 500));
        //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
 
       
        
        //Display the window.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setSize(width/2, height/2);
        
        // center the jframe on screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        
           
        
        JTextArea a = new JTextArea();
        a.setPreferredSize(new Dimension(100, 100));
        
        JPanel contentPane = new JPanel();
        contentPane.add(a, BorderLayout.LINE_START);
        frame.setContentPane(contentPane);   
        
    }
	
}
