package africa.semicolon.myBlogApp;

import africa.semicolon.myBlogApp.data.repository.UserRepository;
import africa.semicolon.myBlogApp.dto.LogInRequest;
import africa.semicolon.myBlogApp.dto.SignInRequest;
import africa.semicolon.myBlogApp.exception.UsernameAlreadyExistsException;
import africa.semicolon.myBlogApp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void initialize(){

        userRepository.deleteAll();
    }


    @Test
    public void logIn() {
        SignInRequest signInRequest = new SignInRequest();
        LogInRequest logInRequest = new LogInRequest();
        signInRequest.setFirstName("Onome");
        signInRequest.setLastName("Precious");
        signInRequest.setUsername("My name");
        signInRequest.setPassword("My password");
        userService.signIn(signInRequest);

        logInRequest.setUsername("My name");
        logInRequest.setPassword("My password");
        assertEquals(1,userRepository.count());
    }

    @Test
   public void logOut() {
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
        userService.logOut(logInRequest);
        assertFalse( userRepository.findUserByUsername(logInRequest.getUsername()).isLoggedIn());


    }

    @Test
  public void test_thatICanRegister() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setFirstName("Onome");
        signInRequest.setLastName("Precious");
        signInRequest.setUsername("My name");
        signInRequest.setPassword("My password");
        userService.signIn(signInRequest);


        assertEquals(1, userRepository.count());


    }
    @Test
    public void testThat_twoPersonsCanRegister() {
        SignInRequest signInRequest = new SignInRequest();
        SignInRequest signInRequest1 = new SignInRequest();
        signInRequest.setFirstName("Onome");
        signInRequest.setLastName("Precious");
        signInRequest.setUsername("My name");
        signInRequest.setPassword("My password");
        signInRequest1.setFirstName("Ayo");
        signInRequest1.setLastName("Mide");
        signInRequest1.setUsername("Midex");
        signInRequest1.setPassword("password");
        userService.signIn(signInRequest);
        userService.signIn(signInRequest1);


        assertEquals(2, userRepository.count());


    }


    @Test
   public void findUserByUsername() {
        SignInRequest signInRequest = new SignInRequest();
        LogInRequest logInRequest = new LogInRequest();
        signInRequest.setFirstName("Onome");
        signInRequest.setLastName("Precious");
        signInRequest.setUsername("My name");
        signInRequest.setPassword("My password");
        userService.signIn(signInRequest);
        logInRequest.setUsername("My name");
        logInRequest.setPassword("My password");
        userService.logIn(logInRequest);

        assertEquals("My name", userRepository.findUserByUsername("My name").getUsername());
    }
    @Test
    public void throwsExceptionWhenITryToRegisterWithAUsernameThatAlreadyExists() {
        SignInRequest signInRequest = new SignInRequest();
        SignInRequest signInRequest1 = new SignInRequest();
        signInRequest.setFirstName("Onome");
        signInRequest.setLastName("Precious");
        signInRequest.setUsername("My name");
        signInRequest.setPassword("My password");
        signInRequest1.setFirstName("Ayo");
        signInRequest1.setLastName("Mide");
        signInRequest1.setUsername("My name");
        signInRequest1.setPassword("password");
        userService.signIn(signInRequest);
        assertThrows(UsernameAlreadyExistsException.class, () -> userService.signIn(signInRequest1));

    }
}