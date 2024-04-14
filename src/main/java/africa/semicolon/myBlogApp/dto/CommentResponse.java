package africa.semicolon.myBlogApp.dto;

import lombok.Data;

@Data
public class CommentResponse {
    private String commenterUsername;
    private String comment;
    private String postContent;
}


