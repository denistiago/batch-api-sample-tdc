package br.com.tecsinapse.batch.mapper;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@Named
public class PartitionIteratorProducer {

    @Produces @PartitionIterator
    public Iterator getPartitionIterator(JobContext jobContext, JobOperator jobOperator) {
        
        Iterator<?> iterator = null;
        long executionId = jobContext.getExecutionId();
        Properties parameters = jobOperator.getParameters(executionId);
        
        List<?> partitionList = (List<?>) parameters.get("PARTITION_LIST");
        
        if (!partitionList.isEmpty()) {
            iterator = partitionList.iterator();
        }
        
        return iterator;
        
    } 
    
}
