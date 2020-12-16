package com.epam.lantsevich.api.tests;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

import com.epam.lantsevich.api.services.RestApiClient;
import io.restassured.response.Response;


public class DeletePosts extends BaseTest {

    @Test
    public void DeletePostsTest() {
        Response response = client.sendRequest(RestApiClient.RequestMethod.DELETE, "posts/1", null, null, null);

        assertStatusCode(response, 200);

        Map<?, ?> actualResponseBody = response.jsonPath().get();
        System.out.println("Actual Response Content:" + actualResponseBody);

        Map<?, ?> expectedResponseBody = Collections.emptyMap();
        System.out.println("Expected Response Content:" + expectedResponseBody);

        assertEquals(expectedResponseBody, actualResponseBody);
    }
}
