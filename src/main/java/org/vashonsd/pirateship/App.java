package org.vashonsd.pirateship;

import java.io.IOException;

import org.vashonsd.pirateship.io.GUI;
import org.vashonsd.pirateship.io.TextAreaOutputStreamTest;

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
    	TextAreaOutputStreamTest trial = new TextAreaOutputStreamTest();
    	gui.Run();
    	g.Run();
    }   
}
