package africa.semicolon.myBlogApp.services;


import africa.semicolon.myBlogApp.dto.CommentRequest;
import africa.semicolon.myBlogApp.dto.CommentResponse;

public interface CommentServices {
    CommentResponse commentOnAPost(CommentRequest commentRequest);
    CommentResponse deleteCommentByRequest(CommentRequest commentRequest);
}
