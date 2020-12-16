package com.epam.lantsevich.api.services;

import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestApiClient {

    Logger logger = LogManager.getLogger(RestApiClient.class);

    private final static String BASE_URL = "http://jsonplaceholder.typicode.com/";
    ;
    private RequestSpecification request;

    private void initRequest() {
        // Set Base URL
        RestAssured.baseURI = BASE_URL;

        // Build request specification
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    public enum RequestMethod {
        GET,
        POST,
        PUT,
        DELETE,
        PATCH
    }

    public Response sendRequest(
            RequestMethod method,
            String apiEndpoint,
            Object body,
            Map<String, ?> queryParams,
            Map<String, ?> headers) {
        return sendRequest(method, BASE_URL, apiEndpoint, body, queryParams, headers);
    }

    public Response sendRequest(
            RequestMethod method,
            String url,
            String apiEndpoint,
            Object body,
            Map<String, ?> queryParams,
            Map<String, ?> headers) {
        initRequest();
        if (headers != null) {
            headers.forEach((headerName, value) -> request.header(headerName, value));
        }

        StringBuilder log = new StringBuilder();
        log.append(String.format("Send %s request:\n%s", method, url + apiEndpoint));

        if (queryParams != null && !queryParams.isEmpty()) {
            log.append("?")
                    .append(queryParams.keySet()
                            .stream()
                            .map(key -> key + "=" + queryParams.get(key))
                            .collect(Collectors.joining("&")));
            request.queryParams(queryParams);
        }
        // Add the Json to the body of the request
        if (body != null) {
            String jsonBody = toPrettyString(body);
            request.body(jsonBody);
            log.append("\n").append(jsonBody);
        }
        logger.info(log.toString());

        Response response;
        switch (method) {
            case GET:
                response = request.get(apiEndpoint);
                break;
            case POST:
                response = request.post(apiEndpoint);
                break;
            case PUT:
                response = request.put(apiEndpoint);
                break;
            case DELETE:
                response = request.delete(apiEndpoint);
                break;
            case PATCH:
                response = request.patch(apiEndpoint);
                break;
            default:
                throw new RuntimeException(String.format(
                        "Identified unsupported request type: '%s'",
                        method.toString()));
        }
        if (response.getBody() != null && !response.getBody().asString().equals("")) {
            logger.info("Received response: \nStatus: " + response.getStatusCode() + "\nBody: \n" +
                    toPrettyString(response.getBody().asString()));
        } else {
            logger.info("Received response: \nStatus: " + response.getStatusCode());
        }
        return response;
    }

    private String toPrettyString(Object obj) {
        try {
            if (obj instanceof String) {
                JsonParser parser = new JsonParser();
                obj = parser.parse((String) obj).getAsJsonObject();
            }
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            return gson.toJson(obj);
        } catch (Throwable e) {
            return obj.toString();
        }
    }
}
