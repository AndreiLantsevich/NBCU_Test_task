package com.epam.lantsevich.api.tests;

import org.junit.Test;

import com.epam.lantsevich.api.model.PostModel;
import com.epam.lantsevich.api.services.RestApiClient;
import io.restassured.response.Response;


public class PatchPosts extends BaseTest {

    @Test
    public void PatchPostsTest() {
        PostModel postModel = new PostModel("patched title", "patched body");
        String postModelAsString = jsonUtil.convertModelToJSON(postModel);

        Response response = client.sendRequest(RestApiClient.RequestMethod.PATCH, "posts/1", postModelAsString, null, null);

        assertStatusCode(response, 200);

        PostModel expectedPostModel = new PostModel(0, 0, "patched title", "patched body");
        assertContent(expectedPostModel, response);
    }
}
