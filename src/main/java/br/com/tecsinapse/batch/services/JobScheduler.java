package br.com.tecsinapse.batch.services;

import br.com.tecsinapse.batch.batchlet.BatchletExecutor;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Singleton
public class JobScheduler {
    
    private static final String JOB_NAME = "fileProcessingJob";
    
    @Resource
    private TimerService timerService;
    
    @Inject
    private BatchletExecutor batchExecutor;
    
    private Timer timer;

    public void schedule(String intervalInSeconds) {
        ScheduleExpression expression = new ScheduleExpression();
        expression.second(String.format("*/%s", intervalInSeconds));
        expression.minute("*");
        expression.hour("*");
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setPersistent(false);
        timer = timerService.createCalendarTimer(expression, timerConfig);
    }
    
    public void cancelSchedule() {
        if(timer != null ) {
            timer.cancel();
        }
    }
    
    @Timeout
    public void execute() {
        batchExecutor.submitJob(JOB_NAME);
    }
    
}
