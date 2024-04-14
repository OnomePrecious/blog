package africa.semicolon.myBlogApp.services;


import africa.semicolon.myBlogApp.dto.ViewRequest;
import africa.semicolon.myBlogApp.dto.ViewResponse;

public interface ViewServices {
    ViewResponse viewPost(ViewRequest viewRequest);
}
