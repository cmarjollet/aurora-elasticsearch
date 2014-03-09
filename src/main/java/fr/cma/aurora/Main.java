package fr.cma.aurora;

import fr.cma.aurora.service.MeasureImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Main implements CommandLineRunner {
    @Autowired
    private MeasureImportService measureImportService;

    @Override
    public void run(String... args) throws IOException {
        measureImportService.load();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
