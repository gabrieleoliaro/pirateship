package org.vashonsd.pirateship.io;

import java.io.IOException;
import java.util.*;

import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.session.XmppSessionConfiguration;
import rocks.xmpp.core.session.debug.ConsoleDebugger;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Presence;
import rocks.xmpp.im.chat.Chat;
import rocks.xmpp.addr.*;

public class XMPPio implements StringRead, StringWrite {

	private XmppClient client;
	private String userMessage = " ";
	private ArrayList<Message> messageQueue;
	private Message currentMessage;
	
	public XMPPio() {
		super();
		TcpConnectionConfiguration tcpConfiguration = TcpConnectionConfiguration.builder()
			    .hostname("xmpp.pirateship.vashonsd.org")
			    .port(5222)
			    .build();
		
		XmppSessionConfiguration config = XmppSessionConfiguration.builder()
			    .debugger(ConsoleDebugger.class)
			    .authenticationMechanisms("PLAIN")
			    .build();

		client = XmppClient.create("xmpp.pirateship.vashonsd.org", config, tcpConfiguration);

		
		messageQueue = new ArrayList<Message>(); //maybe need to go to another place
		
		client.addInboundMessageListener(e -> {
			Message message = e.getMessage();
			messageQueue.add(message);
					
//			for(int i = 0; i < messageQueue.size(); i++)
//			{
//				System.out.println(messageQueue.get(i));
//			}
		});
		
	}
	
	
	public void Run() {
		try {
			   client.connect();
			} catch (XmppException e) {
			   System.out.println("Here's the error: " + e);
			}
		
		try {
			   client.login("pirateship", "mauvian59", "");
			} catch (XmppException e) {
			   System.out.println("Failure of type " + e);
			}
		System.out.println("test");
		while(messageQueue.isEmpty())
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void write(String s) throws IOException {
		Jid recipient = currentMessage.getFrom();
		Message message1 = new Message(recipient, Message.Type.CHAT, s + " from the CPU");
		client.send(message1);
	}

	@Override
	public String read() throws IOException {
		if (messageQueue.isEmpty())
			return " ";
		
		Message toReturn = messageQueue.get(messageQueue.size()-1);
		currentMessage = toReturn;
		messageQueue.remove(messageQueue.size()-1);
		return toReturn.getBody();
	}

}
