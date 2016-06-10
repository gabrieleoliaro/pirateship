package org.vashonsd.pirateship.io;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class AdvancedOutputStream extends OutputStream {

   private volatile String toPrint;
   private final StringBuilder sb = new StringBuilder();
   

   public AdvancedOutputStream() {
      
	toPrint = "";   
   }
   
   public String getToPrint()
   {
	   String toPrintTemp = toPrint;
	   toPrint = "";
	   return toPrintTemp;
   }

   @Override
   public void flush() {
   }

   @Override
   public void close() {
   }

   @Override
   public void write(int b) throws IOException {
	   
      if (b == '\r')
         return;

      if (b == '\n') {
         final String text = sb.toString() + "\n";
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               toPrint += text;
            }
         });
         sb.setLength(0);
         
         return;
      }

      sb.append((char) b);
   }
}