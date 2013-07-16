package br.com.tecsinapse.batch.chunk;

import br.com.tecsinapse.batch.services.JobNotificationService;
import br.com.tecsinapse.batch.services.NotificationMessage;
import javax.batch.api.chunk.listener.AbstractItemProcessListener;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ImageItemProcessorListener extends AbstractItemProcessListener {

    @Inject
    private JobNotificationService notificationService;

    @Override
    public void afterProcess(Object item, Object result) throws Exception {
        NotificationMessage notificationMessage = new NotificationMessage(NotificationMessage.Type.IMAGE,
                "", (String) result);
        notificationService.sendMessage(notificationMessage);
    }
}
