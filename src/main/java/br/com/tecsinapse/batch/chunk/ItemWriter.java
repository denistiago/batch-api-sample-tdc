package br.com.tecsinapse.batch.chunk;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;


@Named
public class ItemWriter extends AbstractItemWriter {

    private static final Logger logger = Logger.getLogger(ItemWriter.class.getName());

    @Override
    public void writeItems(List<Object> items) throws Exception {
        logger.log(Level.INFO, "Thread numero: {0} - Fazendo Download das imagens: {1}", new Object[]{Thread.currentThread().getId(), items});
    }
}
