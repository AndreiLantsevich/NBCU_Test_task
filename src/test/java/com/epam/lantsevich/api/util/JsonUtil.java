package com.epam.lantsevich.api.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {

    public ObjectMapper objectMapper = new ObjectMapper();

    public String convertModelToJSON(Object model) {
        String postModelAsString;
        try {
            postModelAsString = objectMapper.writeValueAsString(model);
            return postModelAsString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<?, ?> convertModelToMap(Object model) {
        Map<?, ?> mappedObject = objectMapper.convertValue(model, Map.class);
        return mappedObject;
    }
}
