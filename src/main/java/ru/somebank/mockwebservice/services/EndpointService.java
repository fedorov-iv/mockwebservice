package ru.somebank.mockwebservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import ru.somebank.mockwebservice.models.Endpoint;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EndpointService {

    private Logger logger = LoggerFactory.getLogger(EndpointService.class);

    @Value("${endpoints.config.folder}")
    private String jsonConfigFolder;

    private List<Endpoint> endpoints = new ArrayList<>();

    public Endpoint findBy(String uri, String method){
        return endpoints.stream()
                .filter(o -> o.getUri().equals(uri) && o.getMethod().equals(method)).findFirst().orElse(null);
    }


    @PostConstruct
    public void init() throws Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        Arrays.stream(ResourceUtils.getFile(jsonConfigFolder).listFiles()).forEach(f -> {
            try{
                endpoints.add(jsonMapper.readValue(f, new TypeReference<Endpoint>() {}));
            }catch (Exception e){
                logger.error("Error reading service config .json file");
            }
        });
    }
}
