package africa.semicolon.myBlogApp.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class SignInRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;


}
