package com.example.demo.utility;

import com.example.demo.vo.RouteData;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RoutingDataParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingDataParser.class);
    public static RouteData routeStatic = new RouteData();

    public static String apiKey = "";

    @Autowired
    ResourceLoader resourceLoader;

    public RouteData parseJonData(String apiKeyStr){
        try {
            apiKey = apiKeyStr;
            Resource resource = resourceLoader.getResource("classpath:route-config.json");
            parseJson(resource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return routeStatic;
    }

    private void parseJson(File iFile) throws IOException {

        // Create a factory for creating a JsonParser instance
        JsonFactory jsonFactory = new JsonFactory();

        // Create a JsonParser instance
        try (JsonParser jsonParser = jsonFactory.createParser(iFile)) {

            // Check the first token
            if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Expected content to be an array");
            }

            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                // Read a contact and do something with it
                RouteData route = readData(jsonParser);
                System.out.println(route);
                if(route.isKeyMatch()) {
                    routeStatic = route;
                    break;
                }
                else
                    route = null;
            }
        }
    }
    private RouteData readData(JsonParser jsonParser) throws IOException {

        // Check the first token
        if (jsonParser.currentToken() != JsonToken.START_OBJECT) {
            throw new IllegalStateException("Expected content to be an object");
        }

        RouteData routeData = new RouteData();

        // Iterate over the properties of the object
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

            // Get the current property name
            String property = jsonParser.getCurrentName();

            // Move to the corresponding value
            jsonParser.nextToken();

            // Evaluate each property name and extract the value
            switch (property) {
                case "api-key":
                    String apiKeyVal = jsonParser.getText();
                    routeData.setKey(apiKeyVal);
                    if(apiKey.equalsIgnoreCase(apiKeyVal))
                        routeData.setKeyMatch(true);
                    break;
                case "url":
                    routeData.setUrl(jsonParser.getText());
                    break;
                case "method":
                    routeData.setMethod(jsonParser.getText());
                    break;
                case "pathParams":
                    List<String> strList = readStringList(jsonParser);
                    routeData.setPathParams(strList);
                    break;
                case "queryParams":
                    List<String> strList2 = readStringList(jsonParser);
                    routeData.setQueryParams(strList2);
                    break;
                case "payload":
                    routeData.setPayload(jsonParser.getText());
                    break;
                // Unknown properties are ignored
            }
        }

        return routeData;
    }
    private List<String> readStringList(JsonParser jsonParser) throws IOException {

        // Check the first token
        if (jsonParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an object");
        }

        List<String> emails = new ArrayList<>();

        // Iterate over the tokens until the end of the array
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

            // Add each element of the array to the list of emails
            emails.add(jsonParser.getText());
        }

        return emails;
    }
}
