package africa.semicolon.myBlogApp.dto;

import africa.semicolon.myBlogApp.data.models.Post;
import lombok.Data;

import java.util.List;

@Data
public class LogInResponse {
    private String username;
    private List<Post> userPosts;
}
