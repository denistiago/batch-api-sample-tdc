package br.com.tecsinapse.batch.controller;

import br.com.tecsinapse.batch.services.JobScheduler;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;


@Named
@Singleton
public class JobSettingsController {

    @Inject
    private JobScheduler jobSchedulerService;
    
    private Boolean scheduleJobExecution;
    private String intervalInSeconds;

    public Boolean getScheduleJobExecution() {
        return scheduleJobExecution;
    }

    public void setScheduleJobExecution(Boolean scheduleJobExecution) {
        this.scheduleJobExecution = scheduleJobExecution;
    }

    public String getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(String intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }
    
    public void applySchedullerChanges() {
        if (scheduleJobExecution) {
            jobSchedulerService.schedule(intervalInSeconds);
        } else {
            jobSchedulerService.cancelSchedule();
        }
    }
}
