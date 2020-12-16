package com.epam.lantsevich.api.tests;

import static com.epam.lantsevich.api.services.RestApiClient.RequestMethod.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.lantsevich.api.model.CommentModel;
import com.epam.lantsevich.api.model.PostModel;
import io.restassured.response.Response;


public class GetPosts extends BaseTest {

    @Test
    public void GetPostsTest() {
        Response response = client.sendRequest(GET, "posts", null, null, null);

        assertStatusCode(response, 200);

        PostModel[] postModels = response.getBody().as(PostModel[].class);
        assertEquals(100, postModels.length);
    }

    @Test
    public void GetPostsByIdTest() {
        Response response = client.sendRequest(GET, "posts/1", null, null, null);

        assertStatusCode(response, 200);

        PostModel expectedPostModel = new PostModel(
                1,
                1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\n" + "suscipit recusandae consequuntur expedita et cum\n" +
                        "reprehenderit molestiae ut ut quas totam\n" +
                        "nostrum rerum est autem sunt rem eveniet architecto");
        assertContent(expectedPostModel, response);
    }

    @Test
    public void GetPostsByIdWithCommentsTest() {
        Response response = client.sendRequest(GET, "posts/1/comments", null, null, null);

        assertStatusCode(response, 200);

        CommentModel[] commentModels = response.getBody().as(CommentModel[].class);
        assertEquals(5, commentModels.length);
    }
}
