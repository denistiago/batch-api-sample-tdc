package br.com.tecsinapse.batch.mapper;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.partition.PartitionPlan;
import javax.batch.api.partition.PartitionPlanImpl;
import javax.inject.Named;

@Named
public class PartitionMapper implements javax.batch.api.partition.PartitionMapper {
    private static final Logger logger = Logger.getLogger(PartitionMapper.class.getName());
    
    private static final int NUM_OF_THREADS = 5;

    private static final List<String> URLS = Lists.newArrayList(
                "http://www.thedevelopersconference.com.br/img/cartaz/2013/CartazTDC2013-Sampa-A3.jpg",
                "http://qconsp.com/sp2012/images/patrocinador/tec-sinapse517c.png?1361569586",
                "http://peregrinacultural.files.wordpress.com/2008/09/a-geek.jpg",
                "http://www.dbcovers.com/imagenes/backdrops/grandes/tron_1982//tron_1982_1.jpg",
                "http://nerdbits.com.br/wp-content/uploads/2013/04/Star-Wars-Episodio-IIV.jpg", 
                "http://osantuario.files.wordpress.com/2013/05/tag-star-trek-spock1.jpg",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTEq0jtPpK872pwBIg_rvH2m_0IUXuCx5Z_usyRAcTyyOI2Xuwu",
                "http://cdn.sejalivre.org/uploads/2012/07/tux-joker_1280.png",
                "http://www.devmedia.com.br/imagens/fotoscolunistas/di-logo-java-blue.png",
                "http://www.globalcode.com.br/images/barras/barra-boletim-TDC2013c.jpg?id=416086&hash=EC705013E9DF8DB18246D3BCB0893C93",
                "http://download.ultradownloads.com.br/wallpaper/277997_Papel-de-Parede-Personagens-de-Paper-Mario_1920x1080.jpg",
                "http://fc01.deviantart.net/fs70/f/2012/116/6/9/back_to_the_future_retro_movie_poster_by_egofantastic-d4xnt7g.jpg",
                "http://fotosefotos.com/admin/foto_img/foto_big/desenho_avengers_assemble_be5edaf4a828eb35afd30cdbc582bb67_Os%20vingadores%20(64).jpg");
    
    public PartitionMapper() {
    }
    
    @Override
    public PartitionPlan mapPartitions() throws Exception {
        
        final PartitionPlan partitionPlan = new PartitionPlanImpl();
        partitionPlan.setThreads(NUM_OF_THREADS);
        
        final int itensPerPartition = URLS.size()/NUM_OF_THREADS;
        
        final List<Properties> propertiesList = new ArrayList<>();
        final List<List<String>> partitions = Lists.partition(URLS, itensPerPartition);
        
        for(List<String> partition : partitions) {
            final Properties properties = new Properties();
            properties.put("PARTITION_LIST", Lists.newArrayList(partition));
            propertiesList.add(properties);
        }
        
        partitionPlan.setPartitions(partitions.size());
        partitionPlan.setPartitionProperties(propertiesList.toArray(new Properties[propertiesList.size()]));
        
        return partitionPlan;
    }
    
}
