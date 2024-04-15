package africa.semicolon.myBlogApp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String username;
    private String title;
    private String content;
    private String postId;
    private LocalDateTime dateCreated;
}
