package br.com.tecsinapse.batch.chunk;

import br.com.tecsinapse.batch.mapper.PartitionIterator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ItemReader extends AbstractItemReader {
    private static final Logger logger = Logger.getLogger(ItemReader.class.getName());

    @Inject @PartitionIterator
    private Iterator iterator;
    
    @Inject
    private JobContext jobContext;

    public ItemReader() {
    }

    @Override
    public String readItem() {
        logger.log(Level.INFO, "ItemReader id: {0}", jobContext.getExecutionId());
        
        if (iterator.hasNext()) {
            return (String) iterator.next();
        }
        return null;
    }
}
