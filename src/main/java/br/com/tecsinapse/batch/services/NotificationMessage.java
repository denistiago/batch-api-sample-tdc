package br.com.tecsinapse.batch.services;


public class NotificationMessage {
    
    public enum Type {
       NOTIFICATION,
       IMAGE
    }
    
    private Type type;
    private String title;
    private String body;

    public NotificationMessage(Type type, String title, String body) {
        this.type = type;
        this.title = title;
        this.body = body;
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
    
}
