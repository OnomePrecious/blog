package africa.semicolon.myBlogApp.controller;

import africa.semicolon.myBlogApp.dto.*;
import africa.semicolon.myBlogApp.exception.PostNotAccessibleException;
import africa.semicolon.myBlogApp.services.CommentServices;
import africa.semicolon.myBlogApp.services.PostServices;
import africa.semicolon.myBlogApp.services.UserService;
import africa.semicolon.myBlogApp.services.ViewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/blog")
public class BlogControllers {
    @Autowired
    private UserService userService;
    @Autowired
    private PostServices postServices;
    @Autowired
    private ViewServices viewServices;
    @Autowired
    private CommentServices commentServices;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signUp(@RequestBody SignInRequest signInRequest) {
        try {
            var result = userService.signIn(signInRequest);
            return new ResponseEntity<>(new ApiResponse(true, result),CREATED);
        } catch (PostNotAccessibleException e) {
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/create-post")
    public ResponseEntity<?> createAPost(@RequestBody PostRequest postRequest){
        try{
            var result =postServices.createAPost(postRequest);
            return new ResponseEntity<>(new ApiResponse(true, result),CREATED);
        }catch (PostNotAccessibleException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),BAD_REQUEST);
        }
    }

    @GetMapping("/view-post")
    public ResponseEntity<?> viewPost(@RequestBody ViewRequest viewRequest){
        try{
            var result =viewServices.viewPost(viewRequest);
            return new ResponseEntity<>(new ApiResponse(true, result),CREATED);
        }catch (PostNotAccessibleException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),BAD_REQUEST);
        }
    }
    @PostMapping("/log-in")
    public ResponseEntity<?> logIn(@RequestBody LogInRequest logInRequest){
        try{
            var result =userService.logIn(logInRequest);
           return new ResponseEntity<>(new ApiResponse(true, result),CREATED);
        }catch (PostNotAccessibleException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
       }
    }
    @PostMapping("/log-out")
    public ResponseEntity<?> logOut(@RequestBody LogInRequest logInRequest) {
        try {
            var result = userService.logOut(logInRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (PostNotAccessibleException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
       @PostMapping("update_post")
               public ResponseEntity<?> updatePost(@RequestBody UpdatePostRequest updatePostRequest){
        try {
            var result = postServices.updatePost(updatePostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (PostNotAccessibleException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }

   }
   @DeleteMapping("delete_post")
    public ResponseEntity<?> deletePost(@RequestBody PostRequest postRequest){
        try{
            var result = postServices.deletePostBy(postRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (PostNotAccessibleException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
   }

@PostMapping("commentOnPost")
    public ResponseEntity<?> commentOnAPost(@RequestBody CommentRequest commentRequest){
        try {
            var result = commentServices.commentOnAPost(commentRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (PostNotAccessibleException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
}
    }



