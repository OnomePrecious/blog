package africa.semicolon.myBlogApp.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserResponse {
    private String title;
    private String content;
    private LocalDateTime dateCreated;

}
