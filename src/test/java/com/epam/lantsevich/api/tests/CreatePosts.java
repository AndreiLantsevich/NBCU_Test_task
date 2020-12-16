package com.epam.lantsevich.api.tests;

import org.junit.Test;

import com.epam.lantsevich.api.model.PostModel;
import com.epam.lantsevich.api.services.RestApiClient;
import io.restassured.response.Response;


public class CreatePosts extends BaseTest {

    @Test
    public void CreatePostsTest() {
        PostModel postModel = new PostModel(1, 101, "title", "body");
        String postModelAsString = jsonUtil.convertModelToJSON(postModel);

        Response response = client.sendRequest(
                RestApiClient.RequestMethod.POST,
                "posts",
                postModelAsString,
                null,
                null);

        assertStatusCode(response, 201);
        assertContent(postModel, response);
    }
}
