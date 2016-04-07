package org.vashonsd.pirateship.io.xmpp;

import java.io.IOException;

import org.vashonsd.pirateship.Game;
import org.vashonsd.pirateship.io.XMPPclient;
import org.vashonsd.pirateship.io.XMPPio;

public class App {

	public static void main(String[] args) throws IOException {
		//XMPPio conn = new XMPPio();
		Game r = new Game("Busytown");
		//conn.Run();
		r.Run();
	}

}
