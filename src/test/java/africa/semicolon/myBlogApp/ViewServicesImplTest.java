package africa.semicolon.myBlogApp;

import africa.semicolon.myBlogApp.data.repository.UserRepository;
import africa.semicolon.myBlogApp.data.repository.ViewRepository;
import africa.semicolon.myBlogApp.dto.LogInRequest;
import africa.semicolon.myBlogApp.dto.PostRequest;
import africa.semicolon.myBlogApp.dto.SignInRequest;
import africa.semicolon.myBlogApp.dto.ViewRequest;
import africa.semicolon.myBlogApp.services.PostServices;
import africa.semicolon.myBlogApp.services.UserService;
import africa.semicolon.myBlogApp.services.ViewServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class ViewServicesImplTest {
    @Autowired
    private ViewServices viewServices;
    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private PostServices postServices;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @BeforeEach
    public void setUp() {
        viewRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
  public void test_thatICanViewPost() {
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

        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setTimeOfView(LocalDateTime.now());
        viewRequest.setContentToView("My content");
        viewRequest.setViewerUsername("This viewer");
        //viewRequest.s
        viewServices.viewPost(viewRequest);

        assertEquals(1, viewRepository.count());
    }
}