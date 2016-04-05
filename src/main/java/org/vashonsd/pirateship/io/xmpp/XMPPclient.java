package org.vashonsd.pirateship.io.xmpp;

import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.session.XmppSessionConfiguration;
import rocks.xmpp.core.session.debug.ConsoleDebugger;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.core.stanza.model.Presence;
import rocks.xmpp.im.chat.Chat;
import rocks.xmpp.addr.*;



public class XMPPclient {
	private XmppClient client;
	private String userMessage = " ";
	
	public XMPPclient() {
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

		
		
		client.addInboundMessageListener(e -> {
			Message message = e.getMessage();
			userMessage = message.toString();
			System.out.println(userMessage.toString() + " test");
					
			Jid recipient = message.getFrom();
			Message message1 = new Message(recipient, Message.Type.CHAT, message + " CPU");
			client.send(message1);
			userMessage = " ";
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
//		client.send(new Presence(Presence.Show.CHAT));
//		Jid recipient = Jid.of("gabrieleoliaro@xmpp.pirateship.vashonsd.org");
//		Message message1 = new Message(recipient, Message.Type.CHAT, "Hi Gabriele!");
//		client.send(message1);
//		client.send(new Presence(Presence.Show.CHAT));
//		Jid recipient2 = Jid.of("andy@xmpp.pirateship.vashonsd.org");
//		Message message2 = new Message(recipient2, Message.Type.CHAT, "Hi Andy!");
//		client.send(message2);
		System.out.println("sleep");
		
		while(true)
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			
//			if(!(userMessage.equals(" "))){
//			recipient = Jid.of("gabrieleoliaro@xmpp.pirateship.vashonsd.org");
//			message1 = new Message(recipient, Message.Type.CHAT, userMessage.toString() + " CPU");
//			client.send(message1);
//			userMessage = " ";
//			}
			//System.exit(0);
		}
	}
}
