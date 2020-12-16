package com.epam.lantsevich.api.tests;


import org.junit.Test;

import com.epam.lantsevich.api.model.PostModel;
import com.epam.lantsevich.api.services.RestApiClient;
import io.restassured.response.Response;


public class UpdatePosts extends BaseTest {

    @Test
    public void UpdatePostsTest() {
        PostModel postModel = new PostModel(1, "updated title", "updated body");
        String postModelAsString = jsonUtil.convertModelToJSON(postModel);

        Response response = client.sendRequest(
                RestApiClient.RequestMethod.PUT,
                "posts/1",
                postModelAsString,
                null,
                null);

        assertStatusCode(response, 200);

        PostModel expectedPostModel = new PostModel(1, 1, "updated title", "updated body");
        assertContent(expectedPostModel, response);
    }
}
