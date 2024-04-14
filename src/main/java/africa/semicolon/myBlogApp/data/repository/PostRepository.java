package africa.semicolon.myBlogApp.data.repository;


import africa.semicolon.myBlogApp.data.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    Post findPostByContent(String postContent);

    List<Post> findPostByPosterUsername(String username);
}
