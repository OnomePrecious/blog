package africa.semicolon.myBlogApp.dto;

import lombok.Data;

@Data
public class LogInRequest {
    private String username;
    private String password;
}
