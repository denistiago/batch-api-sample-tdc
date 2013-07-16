package br.com.tecsinapse.batch.batchlet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.BatchProperty;
import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CriaDiretorio implements Batchlet {

    private static final Logger logger = Logger.getLogger(CriaDiretorio.class.getName());

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

        final String diretorioDownload = diretorioDownloadProperty + "/job";
        final String diretorioArquivos = diretorioArquivosProperty;

        logger.log(Level.INFO, "Diretório {0}", diretorioDownload + "criado com sucesso!");
        logger.log(Level.INFO, "Diretório {0}", diretorioArquivos + "criado com sucesso!");

        final Path dirDownload = Paths.get(diretorioDownload);
        final Path dirArquivos = Paths.get(diretorioArquivos);

        Files.createDirectories(dirDownload);
        Files.createDirectories(dirArquivos);

        return "Sucesso";
    }

    @Override
    public void stop() throws Exception {
    }
}
