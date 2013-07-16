package br.com.tecsinapse.batch.batchlet;

import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BatchletExecutor {
    
    @Inject
    private JobOperator jobOperator;
    

    public long submitJob(String jobName) {
        long executionId = jobOperator.start(jobName, new Properties());
        
        return executionId;
    }



    public long restartJob(long executionId) {
        Properties jobProperties = new Properties();
        long newExecutionId = jobOperator.restart(executionId, jobProperties);
        
        return newExecutionId;
    }
}
