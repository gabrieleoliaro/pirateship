package org.vashonsd.pirateship;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextArea outputTextArea;
	private volatile String toPrint;
	private JLabel lblVhsPirateship;
	private JTextField inputTextField;

	
	
	private Queue<String> input;
	
	public void addInput(String input)
	{
		this.input.add(input);
	}
	
	public Queue<String> getInput()
	{
		Queue<String> toReturn = new LinkedList<String>();
		toReturn = input;
		input.clear();
		return toReturn;
	}
	
	
	/**
	 * Create the frame.
	 */
	public GUI() {
		input = new LinkedList<String>();
		
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
		outputTextArea.setEditable(false);
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
				addInput(inputTextField.getText());
				inputTextField.setText("");
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
	
	public synchronized JTextField getInputTextField()
	{
		return inputTextField;
	}
}
