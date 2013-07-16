package br.com.tecsinapse.batch.ws;

import br.com.tecsinapse.batch.services.NotificationMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class JsonEncoder implements Encoder.Text<NotificationMessage> {

    @Override
    public String encode(NotificationMessage message) throws EncodeException {
        JsonObject jsonMessage = Json.createObjectBuilder()
                .add("type", message.getType().name())
                .add("title", message.getTitle())
                .add("body", message.getBody())
                .build();
        return jsonMessage.toString();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}
