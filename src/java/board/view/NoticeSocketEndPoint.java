/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board.view;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author vigne
 */
@RequestScoped
@ServerEndpoint("/notice")
public class NoticeSocketEndPoint {

        private Set<Session> sessions = new HashSet<>();
        
	@OnOpen
	public void open(Session session) {
		System.out.println(">>> new session: " + session.getId());
                sessions.add(session);
	}
        
        @OnClose
        public void close(Session session)
        {
            System.out.println(">>>> session closed: " + session.getId());
            sessions.remove(session);
        }
 
	@OnMessage
	public void message(String msg, Session session) {
		System.out.println(">>> message: " + msg);

		
				System.out.println(">>> in thread");
				final JsonObject message = Json.createObjectBuilder()
						.add("message", msg)
						.add("timestamp", (new Date()).toString())
						.build();

				for (Session s: session.getOpenSessions())
					try {
                                            System.out.println(">>> send to all" + message.toString());
						s.getBasicRemote().sendText(message.toString());
					} catch(IOException ex) {
						try { s.close(); } catch (IOException e) { e.printStackTrace(); }
					}
			
		System.out.println(">>> exiting message");
	}
}
