package org.vashonsd.pirateship.io.xmpp;

import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.session.XmppSessionConfiguration;
import rocks.xmpp.core.session.debug.ConsoleDebugger;
import rocks.xmpp.core.stanza.model.Message;
import rocks.xmpp.im.chat.Chat;
import rocks.xmpp.addr.*;



public class XMPPclient {
	private XmppClient client;

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
			System.out.println(message.toString());
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
		
		Jid recipient = Jid.of("gabrieleoliaro@xmpp.pirateship.vashonsd.org");
		Message message1 = new Message(recipient, Message.Type.CHAT, "Hi Gabriele!");
		client.send(message1);
		
		while(true)
		{
			
		}
	}
	
}
