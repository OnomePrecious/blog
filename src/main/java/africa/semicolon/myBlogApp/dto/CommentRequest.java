package africa.semicolon.myBlogApp.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private String commenterUsername;
    private String comment;
    private String postContent;
}
