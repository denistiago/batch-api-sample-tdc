package br.com.tecsinapse.batch.ws;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws/jobMonitor",
        encoders = JsonEncoder.class)
public class JobProcessingMonitorWebSocket {
    
    @Inject
    private Event<Session> newSessionEvent;
    
    @OnOpen
    public void onOpen(Session session) { 
        newSessionEvent.fire(session);
    }
    
}
