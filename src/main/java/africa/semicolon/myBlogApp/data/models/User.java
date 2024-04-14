package africa.semicolon.myBlogApp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Users")
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean loggedIn;
    @Id
    private String id;
    private List<Post> posts;
}
