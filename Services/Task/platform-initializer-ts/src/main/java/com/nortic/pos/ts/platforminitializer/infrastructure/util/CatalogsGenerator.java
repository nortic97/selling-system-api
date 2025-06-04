package com.nortic.pos.ts.platforminitializer.infrastructure.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nortic.pos.models.document.CatalogDocument;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class CatalogsGenerator {

    private static final String COUNTRY_API = "https://restcountries.com/v3.1/all";
    private static final String COUNTRIES_STATES_CITIES_JSON = "https://raw.githubusercontent.com/dr5hn/countries-states-cities-database/master/json/countries%2Bstates%2Bcities.json";

    public static List<CatalogDocument> generateInitialCatalogs() {
        List<CatalogDocument> catalogs = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Obtener datos de países desde restcountries
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest countryRequest = HttpRequest.newBuilder()
                    .uri(URI.create(COUNTRY_API))
                    .GET()
                    .build();

            HttpResponse<String> countryResponse = client.send(countryRequest, HttpResponse.BodyHandlers.ofString());
            JsonNode countryRoot = mapper.readTree(countryResponse.body());

            // Mapear información adicional de países
            Map<String, Map<String, Object>> countryAttributesMap = new HashMap<>();
            for (JsonNode node : countryRoot) {
                String countryCode = node.path("cca2").asText();
                String phoneCode = node.path("idd").path("root").asText("") +
                        Optional.ofNullable(node.path("idd").path("suffixes"))
                                .filter(JsonNode::isArray)
                                .map(suffixes -> suffixes.get(0).asText(""))
                                .orElse("");

                Map<String, Object> attributes = new HashMap<>();
                attributes.put("phoneCode", phoneCode);
                attributes.put("region", node.path("region").asText());
                attributes.put("subregion", node.path("subregion").asText());
                attributes.put("flag", node.path("flag").asText());

                countryAttributesMap.put(countryCode, attributes);

                // Monedas
                if (node.has("currencies")) {
                    Iterator<String> codes = node.get("currencies").fieldNames();
                    while (codes.hasNext()) {
                        String currencyCode = codes.next();
                        JsonNode currencyNode = node.get("currencies").get(currencyCode);

                        catalogs.add(CatalogDocument.builder()
                                .id("currency:" + currencyCode)
                                .type("currency")
                                .code(currencyCode)
                                .name(currencyNode.path("name").asText())
                                .description("Currency catalog item")
                                .attributes(Map.of(
                                        "symbol", currencyNode.path("symbol").asText("")
                                ))
                                .build()
                        );
                    }
                }
            }

            // Obtener datos de países, estados y ciudades desde el archivo JSON
            HttpRequest cscRequest = HttpRequest.newBuilder()
                    .uri(URI.create(COUNTRIES_STATES_CITIES_JSON))
                    .GET()
                    .build();

            HttpResponse<String> cscResponse = client.send(cscRequest, HttpResponse.BodyHandlers.ofString());
            JsonNode cscRoot = mapper.readTree(cscResponse.body());

            for (JsonNode countryNode : cscRoot) {
                String countryCode = countryNode.path("iso2").asText();
                String countryName = countryNode.path("name").asText();

                Map<String, Object> attributes = countryAttributesMap.getOrDefault(countryCode, new HashMap<>());

                List<Map<String, Object>> statesList = new ArrayList<>();
                for (JsonNode stateNode : countryNode.path("states")) {
                    String stateName = stateNode.path("name").asText();
                    String stateCode = stateNode.path("state_code").asText();

                    List<String> cities = new ArrayList<>();
                    for (JsonNode cityNode : stateNode.path("cities")) {
                        cities.add(cityNode.path("name").asText());
                    }

                    Map<String, Object> stateMap = new HashMap<>();
                    stateMap.put("name", stateName);
                    stateMap.put("code", stateCode);
                    stateMap.put("cities", cities);

                    statesList.add(stateMap);
                }

                attributes.put("states", statesList);

                catalogs.add(CatalogDocument.builder()
                        .id("country:" + countryCode)
                        .type("country")
                        .code(countryCode)
                        .name(countryName)
                        .description("Country catalog item with states and cities")
                        .attributes(attributes)
                        .build()
                );
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error generando catálogos: " + e.getMessage());
        }

        return catalogs;
    }
}
