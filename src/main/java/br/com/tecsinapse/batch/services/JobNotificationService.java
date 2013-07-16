package br.com.tecsinapse.batch.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.websocket.Session;


@Named
@Singleton
public class JobNotificationService {
    
    private Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    
    public void onSessionSubscribe(@Observes Session session) {
        sessions.add(session);
    }
    
    public void sendMessage(NotificationMessage message) {
        for( Session s : sessions ) {
            s.getAsyncRemote().sendObject(message);
        }
    }
}
