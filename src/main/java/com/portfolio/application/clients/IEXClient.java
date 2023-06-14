package com.portfolio.application.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.application.model.Holding;
import com.portfolio.application.model.Security;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class IEXClient {

    public static Holding enrichHoldingDetail(String symbol, Holding holding) throws URISyntaxException, IOException, InterruptedException {
        Security security = getSecurityInfo(symbol);
        holding.setSecurity(security);
        holding.setHoldingValue(holding.getUnits()*security.getLatestPrice());
        return holding;
    }

    public static Security getSecurityInfo(String symbol) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(String.format("https://api.iex.cloud/v1/data/core/quote/%s?token=sk_73093895a84b4a03923cb579d25bdd38", symbol)))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readValue(response.body(), JsonNode.class);
        String jsonArrayAsString = objectMapper.writeValueAsString(jsonArray);

        return objectMapper.readValue(jsonArrayAsString, new TypeReference<List<Security>>() {}).get(0);
    }


}
