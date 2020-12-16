package com.epam.lantsevich.api.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.epam.lantsevich.api.model.CommentModel;
import com.epam.lantsevich.api.services.RestApiClient;
import io.restassured.response.Response;


public class GetComments extends BaseTest {

    @Test
    public void GetCommentsTest() {
        Map queryParams = new HashMap<>();
        queryParams.put("postId", 1);
        Response response = client.sendRequest(RestApiClient.RequestMethod.GET, "comments", null, queryParams, null);

        assertStatusCode(response, 200);

        CommentModel[] commentModels = response.getBody().as(CommentModel[].class);
        assertEquals(5, commentModels.length);
    }
}
