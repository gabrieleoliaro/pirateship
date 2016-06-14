package org.vashonsd.pirateship;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.vashonsd.pirateship.interactions.Player;
import org.vashonsd.pirateship.interactions.Request;
import org.vashonsd.pirateship.io.*;
import org.vashonsd.pirateship.item.*;
import org.vashonsd.pirateship.structure.*;
import org.vashonsd.pirateship.minigame.*;

public class Game {
	private StringRead reader;
	private StringWrite writer;
	
	private DatabaseWriter db = new DatabaseWriter();
	
	//This is our register of current Players, each with a unique ID.
	private PlayerRegistry players;
	
	//private HashMap<String, Player> players;
	
	private String quitWord;
	
	private  Queue<String> input;
	
	public Game(String world) throws IOException {
		super();
		
		
		quitWord = "exit";

    	//thisWorld = WorldBuilder.makeWorldByFile(world);
		World thisWorld = WorldBuilder.makeWorld(world);
		
		this.players = new PlayerRegistry();
		Player p = new Player("Demo", "Just a player");
		p.setLocation(thisWorld.getStartingLocation());
		String pid = players.EnrollPlayer(p);
		
    	reader = new UserInput();
    	writer = new ConsoleOut();
    	
    	GUI gui = new GUI();
    	
    	
    	AdvancedOutputStream output = new AdvancedOutputStream();
    	System.setOut(new PrintStream(output));
    	input = new LinkedList<String>();
    	
    	
    	//PrintStream stdout = System.out;
    	//System.setOut(stdout);
    	
    	Thread t1 = new Thread(new Runnable() 
		{
			@Override
			public void run() {
				
				
				while(true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						gui.setTextConsole(output.getToPrint());
						
						Queue<String> newInput = new LinkedList<String>();
						newInput = gui.getInput();
						for(String currentInput : newInput)
						{
							input.add(currentInput);
						}
					}
				}
				
			
			
			
			
			
		});
		
		t1.start();
		
    	
    	
	}
	
	public void Run() throws IOException {
		//For now we are just going to take the first Player off the registry.
		//Later we will want to be able to enroll players on the fly.
		Player p = players.get("Demo");
		writer.write(p.handle("look").getText() + "\n");
		
		Thread handleConsole = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					String command = "";
					
					try {
						command = reader.read();
						writer.write(p.handle(command).getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (p.getTarget() == null && command.equals(quitWord)) {
						break;
					}
				}
			}
		});
		handleConsole.start();
		
		Thread handleGui = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					String command = "";
					
					for(String currentInput : input)
					{
						command = currentInput;
						try {
							writer.write(p.handle(command).getText());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (p.getTarget() == null && command.equals(quitWord)) {
							break;
						}
					}
				}
			}
		});
		handleGui.start();
		
		writer.write("Thanks for playing!");
	}
}
