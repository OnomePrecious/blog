package africa.semicolon.myBlogApp.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ViewResponse {
        private String contentViewed;
        private String viewerUsername;
        private LocalDateTime timeOfView;

    }
