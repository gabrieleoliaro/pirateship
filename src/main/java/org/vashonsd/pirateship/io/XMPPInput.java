package org.vashonsd.pirateship.io;

import java.io.IOException;
import java.util.ArrayList;

public class XMPPInput implements StringRead {
	private ArrayList<String> queue;

	@Override
	public String read() throws IOException {
		return "";
	}

	public void enqueueMessage(String m) {
		queue.add(m);
	}
}
