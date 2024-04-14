package africa.semicolon.myBlogApp;

import africa.semicolon.myBlogApp.data.repository.CommentRepository;
import africa.semicolon.myBlogApp.data.repository.UserRepository;
import africa.semicolon.myBlogApp.dto.CommentRequest;
import africa.semicolon.myBlogApp.dto.LogInRequest;
import africa.semicolon.myBlogApp.dto.PostRequest;
import africa.semicolon.myBlogApp.dto.SignInRequest;
import africa.semicolon.myBlogApp.services.CommentServices;
import africa.semicolon.myBlogApp.services.PostServices;
import africa.semicolon.myBlogApp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class CommentServicesImplTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostServices postServices;
    @Autowired
    private CommentServices commentServices;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
   public void setUp() {
        commentRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void test_ThatICanCommentOnAPost() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setFirstName("Onome");
        signInRequest.setLastName("Precious");
        signInRequest.setUsername("My name");
        signInRequest.setPassword("My password");
        userService.signIn(signInRequest);

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername("My name");
        logInRequest.setPassword("My password");
        userService.logIn(logInRequest);

        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("My name");
        postRequest.setContent("My content");
        postRequest.setTitle("My title");
        postRequest.setTimeOfPostCreated(LocalDateTime.now());
        postServices.createAPost(postRequest);

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setComment("This is nice");
        commentRequest.setCommenterUsername("Precious");
        //commentRequest.setPostContent("Programming");
        commentServices.commentOnAPost(commentRequest);
        userRepository.findUserByUsername("");

        assertEquals(1, commentRepository.count());


    }

    @Test
    void deleteCommentByRequest() {
    }
}