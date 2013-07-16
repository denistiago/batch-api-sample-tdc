package br.com.tecsinapse.batch.batchlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.batch.api.BatchProperty;
import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CompactaArquivos implements Batchlet {

    private static final int BYTE_SIZE = 1024;

    @Inject
    private JobContext jobContext;
    
    @Inject
    @BatchProperty(name = "diretorioDownload")
    private String diretorioDownloadProperty;
    
    @Inject
    @BatchProperty(name = "diretorioArquivos")
    private String diretorioArquivosProperty;

    @Override
    public String process() throws Exception {
        
        final String diretorioDownload = diretorioDownloadProperty + File.separator + "job";
        final String outputZipFile = diretorioArquivosProperty + File.separator + "job" + ".zip";
        
        final List<String> fileList = listFiles(new File(diretorioDownload));

        zipFiles(outputZipFile, diretorioDownload, fileList);

        return "Sucesso";
    }

    @Override
    public void stop() throws Exception {
    }

    private void zipFiles(final String zipFile, final String sourceFolder, final List<String> fileList) throws IOException {
        
        final byte[] buffer = new byte[BYTE_SIZE];

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {

            for (String file : fileList) {
                final ZipEntry zipEntry = new ZipEntry(file);
                zos.putNextEntry(zipEntry);

                final FileInputStream fis = new FileInputStream(sourceFolder + File.separator + file);
                final BufferedInputStream bis = new BufferedInputStream(fis);

                int bytesLidos;

                while ((bytesLidos = bis.read(buffer)) != -1) {
                    zos.write(buffer, 0, bytesLidos);
                }
            }
        }
    }

    private List<String> listFiles(File file) {

        String[] children;

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return dir.isDirectory();
            }
        };

        children = file.list(filter);

        return Arrays.asList(children);
    }
}
