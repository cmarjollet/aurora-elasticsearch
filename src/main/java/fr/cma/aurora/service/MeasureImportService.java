package fr.cma.aurora.service;

import fr.cma.aurora.repository.MeasureRepository;
import fr.cma.aurora.model.Measure;
import fr.cma.aurora.model.MeasureList;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;

@Service
public class MeasureImportService {
    private static final Logger logger = LoggerFactory.getLogger(MeasureImportService.class);

    @Autowired
    private MeasureRepository measureRepository;

    @Value("${noaa.data.first.year}")
    private int firstYear;

    @Value("${noaa.data.last.year}")
    private int lastYear;

    @Value("${noaa.data.base.url}")
    private String baseUrl;

    @Value("${noaa.data.base.directory}")
    private String baseDirectory;

    public void load() throws IOException {
        for (int year = firstYear; year <= lastYear; year++) {
            if (year != 1989 && year != 1990) {
                logger.info("Parsing data of {}", year);
                MeasureList dataFile = loadDataFromNoaaWebsite(year);
                saveDataInElasticsearchRepository(dataFile);
            }
        }
    }

    private MeasureList loadDataFromNoaaWebsite(int year) throws IOException {
        String filename = "power_" + year + ".txt";
        File file = new File(baseDirectory, filename);

        if (!file.exists()) {
            logger.info("Load data from web site for year {}", year);
            InputStream input = new URL(baseUrl + filename).openStream();
            OutputStream output = new FileOutputStream(file);
            IOUtils.copy(input, output);
        }
        return new MeasureList(year, new FileInputStream(file));
    }

    private void saveDataInElasticsearchRepository(MeasureList dataFile) {
        for (Measure measure : dataFile) {
            measureRepository.save(measure);
        }
    }
}
