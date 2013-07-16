package br.com.tecsinapse.batch.listener;

import br.com.tecsinapse.batch.services.JobNotificationService;
import br.com.tecsinapse.batch.services.NotificationMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.listener.JobListener;
import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;


@Named
public class JobBatchListener implements JobListener, StepListener {
    
    private static final Logger logger = Logger.getLogger(JobBatchListener.class.getName());
    
    @Inject
    private JobContext jobContext;
    
    @Inject
    private StepContext stepContext;
    
    @Inject
    private JobNotificationService jobNotificationService;

    @Override
    public void beforeJob() throws Exception {
        logger.log(Level.INFO, "Job starting {0}", jobContext.getJobName());
        jobNotificationService.sendMessage(new NotificationMessage(NotificationMessage.Type.NOTIFICATION, 
                "Job Notification", "Starting new job execution"));
        
    }

    @Override
    public void afterJob() throws Exception {
        logger.log(Level.INFO, "Job finished");
        jobNotificationService.sendMessage(new NotificationMessage(NotificationMessage.Type.NOTIFICATION, 
                "Job Notification", "Finished job execution"));
    }

    @Override
    public void beforeStep() throws Exception {
        logger.log(Level.INFO, "Start step {0}", stepContext.getStepName());
    }

    @Override
    public void afterStep() throws Exception {
        logger.log(Level.INFO, "Step finished");
    }
    
}
