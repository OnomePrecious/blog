package africa.semicolon.myBlogApp.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ViewRequest {
    private String contentToView;
    private String viewerUsername;
    private LocalDateTime timeOfView;
}
