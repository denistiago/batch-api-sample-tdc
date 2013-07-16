package br.com.tecsinapse.batch.chunk;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.BatchProperty;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ItemProcessor implements javax.batch.api.chunk.ItemProcessor {
    private static final Logger logger = Logger.getLogger(ItemProcessor.class.getName());
    
    private static final int BYTE_SIZE = 1024;

    @Inject
    private JobContext jobContext;
    
    @Inject
    @BatchProperty(name = "diretorioDownload")
    private String diretorioDownloadProperty;
    
    @Override
    public Object processItem(Object fileUrl) throws Exception {
        final long executionId = jobContext.getExecutionId();
        final String diretorioDownload = diretorioDownloadProperty + "/job";
        final String urlFile = (String) fileUrl;
        final URL url = new URL(urlFile);
        
        logger.log(Level.INFO, "ItemProcessor id: {0}", executionId);
        
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        HttpURLConnection.setFollowRedirects(true);

        final InputStream inputStream = conn.getInputStream();

        final String fileName = diretorioDownload + "/downl_image_job_" + executionId + "_" + urlFile.substring(urlFile.length() - 6);

        final byte[] buffer = new byte[BYTE_SIZE];
        
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)))) {
            
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
        }

        return fileName;
    }
    
}
