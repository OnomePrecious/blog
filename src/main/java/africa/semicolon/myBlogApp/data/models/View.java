package africa.semicolon.myBlogApp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Views")
public class View {
    private LocalDateTime timeOfView;
    private User viewer;
    @Id
    private String id;
}
