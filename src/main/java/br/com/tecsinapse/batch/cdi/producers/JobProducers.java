package br.com.tecsinapse.batch.cdi.producers;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.inject.Produces;
import javax.inject.Named;


@Named
public class JobProducers {
    
    @Produces
    public JobOperator getJobOperator() {
        return BatchRuntime.getJobOperator();
    }
}
