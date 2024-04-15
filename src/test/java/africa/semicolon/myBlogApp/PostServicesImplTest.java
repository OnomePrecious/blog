package africa.semicolon.myBlogApp;

import africa.semicolon.myBlogApp.data.repository.PostRepository;
import africa.semicolon.myBlogApp.data.repository.UserRepository;
import africa.semicolon.myBlogApp.dto.LogInRequest;
import africa.semicolon.myBlogApp.dto.PostRequest;
import africa.semicolon.myBlogApp.dto.SignInRequest;
import africa.semicolon.myBlogApp.dto.UpdatePostRequest;
import africa.semicolon.myBlogApp.services.PostServices;
import africa.semicolon.myBlogApp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostServicesImplTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostServices postServices;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @BeforeEach
    public void initialize(){
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
   public void test_thatICanCreateAPost() {
        SignInRequest signInRequest = new SignInRequest();
        LogInRequest logInRequest = new LogInRequest();
        PostRequest postRequest = new PostRequest();
        signInRequest.setFirstName("Onome");
        signInRequest.setLastName("Precious");
        signInRequest.setUsername("My name");
        signInRequest.setPassword("My password");
        userService.signIn(signInRequest);

       logInRequest.setUsername("My name");
       logInRequest.setPassword("My password");
       userService.logIn(logInRequest);

        postRequest.setUsername("My name");
        postRequest.setContent("My content");
        postRequest.setTitle("My title");
        postRequest.setTimeOfPostCreated(LocalDateTime.now());
        postServices.createAPost(postRequest);


        assertEquals(1, postRepository.count());
    }
    @Test
    public void test_thatICanCreateAPostTwice() {
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

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setUsername("My name");
        postRequest1.setContent("My second content");
        postRequest1.setTitle("My second title");
        postRequest1.setTimeOfPostCreated(LocalDateTime.now());
        postServices.createAPost(postRequest);
        postServices.createAPost(postRequest1);
        assertEquals(2, postRepository.count());


    }

        @Test
    public void test_thatICanDeleteAPost() {
            SignInRequest signInRequest = new SignInRequest();
            LogInRequest logInRequest = new LogInRequest();
            PostRequest postRequest = new PostRequest();
            PostRequest postRequest1 = new PostRequest();
            signInRequest.setFirstName("Onome");
            signInRequest.setLastName("Precious");
            signInRequest.setUsername("My name");
            signInRequest.setPassword("My password");
            userService.signIn(signInRequest);

            logInRequest.setUsername("My name");
            logInRequest.setPassword("My password");
            userService.logIn(logInRequest);

            postRequest.setUsername("My name");
            postRequest.setContent("My content");
            postRequest.setTitle("My title");
            postRequest.setPostId("My id");
            postRequest.setTimeOfPostCreated(LocalDateTime.now());
            postRequest1.setUsername("My name");
            postRequest1.setContent("My second content");
            postRequest1.setTitle("My second title");
            postRequest1.setTimeOfPostCreated(LocalDateTime.now());
            postServices.createAPost(postRequest);
            postServices.createAPost(postRequest1);
            postServices.deletePostBy(postRequest);
            assertEquals(1, postRepository.count());
    }

    @Test
    public void testThatICanUpdatePost() {
        SignInRequest signInRequest = new SignInRequest();
        LogInRequest logInRequest = new LogInRequest();
        PostRequest postRequest = new PostRequest();
        UpdatePostRequest updatePostRequest = new UpdatePostRequest();
        signInRequest.setFirstName("Onome");
        signInRequest.setLastName("Precious");
        signInRequest.setUsername("My name");
        signInRequest.setPassword("My password");
        userService.signIn(signInRequest);

        logInRequest.setUsername("My name");
        logInRequest.setPassword("My password");
        userService.logIn(logInRequest);

        postRequest.setUsername("My name");
        postRequest.setContent("My content");
        postRequest.setTitle("My title");
        postRequest.setTimeOfPostCreated(LocalDateTime.now());
        postServices.createAPost(postRequest);
        updatePostRequest.setUsername("My name");
        updatePostRequest.setContent("My content");
        updatePostRequest.setTitle("This title");
        postServices.updatePost(updatePostRequest);
        assertEquals("This title", postRepository.findPostByContent("My content").getTitle());



    }

    @Test
    public void test_thatICanFindAllPost() {
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

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setUsername("My name");
        postRequest1.setContent("My second content");
        postRequest1.setTitle("My second title");
        postRequest1.setTimeOfPostCreated(LocalDateTime.now());

        PostRequest postRequest2 = new PostRequest();
        postRequest2.setUsername("My name");
        postRequest2.setTitle("Story of my life");
        postRequest2.setContent("It all began");
        postRequest2.setTimeOfPostCreated(LocalDateTime.now());
        postServices.createAPost(postRequest);
        postServices.createAPost(postRequest1);
        postServices.createAPost(postRequest2);
        assertEquals(3, postRepository.count());

    }
}