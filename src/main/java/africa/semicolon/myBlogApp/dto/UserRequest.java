package africa.semicolon.myBlogApp.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserRequest {
    private String username;
    private String title;
    private String content;
    private LocalDateTime dateCreated;
}
