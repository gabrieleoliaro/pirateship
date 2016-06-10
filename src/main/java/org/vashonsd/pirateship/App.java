package org.vashonsd.pirateship;

import java.io.IOException;
import java.io.PrintStream;

import org.vashonsd.pirateship.io.*;

/**
 * We keep the App very sparse. It's just the trigger.
 *
 */
public class App 
{	
    
	
	public static void main( String[] args ) throws IOException
    {
    	Game g = new Game("Busytown");
    	
    	
    	GUI gui = new GUI();
    	
    	
    	AdvancedOutputStream output = new AdvancedOutputStream();
    	PrintStream stdout = System.out;
    	System.setOut(new PrintStream(output));
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
					}
				}
				
			
			
			
			
			
		});
		
		t1.start();
		
		
    	
		g.Run();
    }   
}
