package fr.cma.aurora.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cma.aurora.model.Measure;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MeasureRepository {
    @Autowired
    private Client elasticsearchClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${elasticsearch.index}")
    private String elasticsearchIndex;

    @Value("${elasticsearch.model}")
    private String elasticsearchModel;

    public void save(Measure measure) {
        elasticsearchClient.prepareIndex(elasticsearchIndex, elasticsearchModel)
            .setSource(toJson(measure))
            .execute()
            .actionGet();
    }

    private String toJson(Measure measure) {
        try {
            return objectMapper.writeValueAsString(measure);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
