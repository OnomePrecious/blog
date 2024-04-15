package africa.semicolon.myBlogApp.util;


import africa.semicolon.myBlogApp.data.models.Comment;
import africa.semicolon.myBlogApp.data.models.Post;
import africa.semicolon.myBlogApp.data.models.User;
import africa.semicolon.myBlogApp.dto.*;

import java.time.LocalDateTime;

public class Mappers {
    public static void mapUserRequestToSignIn(SignInRequest signInRequest, User user){
        user.setFirstName(signInRequest.getFirstName());
        user.setLastName(signInRequest.getLastName());
        user.setUsername(signInRequest.getUsername());
        user.setPassword(signInRequest.getPassword());
    }
     public static SignInResponse mapUserSignInResponse(User user){
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setFirstname(user.getFirstName());
        signInResponse.setUsername(user.getUsername());
        signInResponse.setLastname(user.getLastName());
         return signInResponse;
     }
       public static LogInResponse mapUserLogInResponse(User user, LogInResponse logInResponse){
        logInResponse.setUsername(user.getUsername());
        logInResponse.setUserPosts(user.getPosts());
           return logInResponse;
       }
       public static void mapUserPostRequest(PostRequest postRequest, Post post){
        post.setPosterUsername(postRequest.getUsername());
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setDateCreated(LocalDateTime.now());
       }

       public static PostResponse mapPostToResponse(Post post){
        PostResponse postResponse = new PostResponse();
        postResponse.setUsername(post.getPosterUsername());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setDateCreated(LocalDateTime.now());
           return postResponse;
       }
    public static void mapCommentRequestToComment(CommentRequest commentRequest, Comment comment){
        comment.setComment(commentRequest.getComment());
        comment.setPostCommentedOn(commentRequest.getPostContent());
    }
    public static CommentResponse mapCommentRequestToResponse(CommentRequest commentRequest){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setComment(commentResponse.getComment());
        commentResponse.setCommenterUsername(commentRequest.getCommenterUsername());
        commentResponse.setPostContent(commentRequest.getPostContent());
        return commentResponse;
    }
    public static void mapUpdateRequestToPost(UpdatePostRequest updatePostRequest, Post post){
        post.setPosterUsername(updatePostRequest.getUsername());
        post.setTitle(updatePostRequest.getTitle());

    }
    public static ViewResponse mapViewRequestToResponse(ViewRequest viewRequest){
        ViewResponse viewResponse = new ViewResponse();
        viewResponse.setContentViewed(viewRequest.getContentToView());
        viewResponse.setTimeOfView(LocalDateTime.now());
        viewResponse.setViewerUsername(viewRequest.getViewerUsername());
        return viewResponse;
    }
}
