package com.epam.lantsevich.api.tests;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lantsevich.api.services.RestApiClient;
import com.epam.lantsevich.api.util.JsonUtil;
import io.restassured.response.Response;


public class BaseTest {

    Logger logger = LogManager.getLogger(BaseTest.class);

    protected RestApiClient client;
    protected JsonUtil jsonUtil;

    public BaseTest() {
        this.jsonUtil = new JsonUtil();
        this.client = new RestApiClient();
    }

    public void assertStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        logger.info("Response Status Code: " + actualStatusCode);
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    public void assertContent(Object postModel, Response response) {
        Map<?, ?> actualResponseBody = response.jsonPath().get();
        logger.info("Actual Response Content:" + actualResponseBody);

        Map<?, ?> expectedResponseBody = jsonUtil.convertModelToMap(postModel);
        logger.info("Expected Response Content:" + expectedResponseBody);

        assertEquals(expectedResponseBody, actualResponseBody);
    }
}
