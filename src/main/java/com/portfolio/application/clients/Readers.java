package com.portfolio.application.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.application.model.Portfolio;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Readers {
    public static List<Portfolio> readPortfolios() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = Readers.class.getClassLoader();
        File file = new File(classLoader.getResource("portfolios.json").getFile());

        JsonNode jsonArray = objectMapper.readValue(file, JsonNode.class);
        String jsonArrayAsString = objectMapper.writeValueAsString(jsonArray);

        return objectMapper.readValue(jsonArrayAsString, new TypeReference<List<Portfolio>>() {});

    }
}
