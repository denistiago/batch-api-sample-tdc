package br.com.tecsinapse.batch.batchlet;

import java.util.logging.Logger;
import javax.batch.api.Batchlet;
import javax.inject.Named;

@Named
public class SimpleExampleBatchlet implements Batchlet{
    
    private static final Logger logger = Logger.getLogger(SimpleExampleBatchlet.class.getName());

    @Override
    public String process() throws Exception {
        logger.info("Ola Mundo");
        
        return "Sucesso";
    }

    @Override
    public void stop() throws Exception {
    }
    
}
