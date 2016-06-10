package org.vashonsd.pirateship;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextArea outputTextArea;
	private volatile String toPrint;
	private JLabel lblVhsPirateship;
	private JTextField inputTextField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 781, 314);
		contentPane.add(scrollPane);
		
		outputTextArea = new JTextArea();
		scrollPane.setViewportView(outputTextArea);
		
		lblVhsPirateship = new JLabel("VHS PirateShip");
		lblVhsPirateship.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVhsPirateship.setBounds(317, 11, 150, 31);
		contentPane.add(lblVhsPirateship);
		
		inputTextField = new JTextField();
		inputTextField.setBounds(10, 378, 672, 37);
		contentPane.add(inputTextField);
		inputTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(692, 378, 99, 37);
		contentPane.add(btnNewButton);
		
		
		
		Thread t1 = new Thread(new Runnable() 
		{
			@Override
			public void run() {
				while(true)
				{
					
					if(toPrint != null && !toPrint.equals("") && !toPrint.equals("null"))
					{
						outputTextArea.append(toPrint + "\n");
						outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
						toPrint = "";
					}
					
					
				}
				
				
			}
			
			
			
			
		});
		
		t1.start();
		
		
		this.setVisible(true);
		
	}
	
	public synchronized void setTextConsole(String text)
	{
		toPrint = "" + text;
	}
}
