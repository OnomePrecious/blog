package africa.semicolon.myBlogApp.services;

import africa.semicolon.myBlogApp.data.models.Post;
import africa.semicolon.myBlogApp.dto.PostRequest;
import africa.semicolon.myBlogApp.dto.PostResponse;
import africa.semicolon.myBlogApp.dto.UpdatePostRequest;

import java.util.List;

public interface PostServices {
    PostResponse createAPost(PostRequest postRequest);
    PostResponse deletePostBy(PostRequest postRequest);
    PostResponse updatePost(UpdatePostRequest updatePostRequest);
    List<Post> findAllPost();
}
