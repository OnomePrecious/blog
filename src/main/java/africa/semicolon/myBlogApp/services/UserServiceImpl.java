package africa.semicolon.myBlogApp.services;

import africa.semicolon.myBlogApp.data.models.User;
import africa.semicolon.myBlogApp.data.repository.UserRepository;
import africa.semicolon.myBlogApp.dto.LogInRequest;
import africa.semicolon.myBlogApp.dto.LogInResponse;
import africa.semicolon.myBlogApp.dto.SignInRequest;
import africa.semicolon.myBlogApp.dto.SignInResponse;
import africa.semicolon.myBlogApp.exception.UnableToLogInException;
import africa.semicolon.myBlogApp.exception.UnableToLogOutException;
import africa.semicolon.myBlogApp.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.myBlogApp.util.Mappers.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public LogInResponse logIn(LogInRequest logInRequest) {
        LogInResponse logInResponse = new LogInResponse();
        var user = findUserByUsername(logInRequest.getUsername());
        if(user.getPassword().equalsIgnoreCase(logInRequest.getPassword())) user.setLoggedIn(true);
        else throw new UnableToLogInException("you must have signed in before you can login");
        userRepository.save(user);
        return mapUserLogInResponse(user,logInResponse);
    }
private boolean isAValidUser(User user){
        return userRepository.findUserByUsername(user.getUsername()) == null;
}
    @Override
    public LogInResponse logOut(LogInRequest logInRequest) {
        LogInResponse logInResponse = new LogInResponse();
        var user = findUserByUsername(logInRequest.getUsername());
        if(user.isLoggedIn()) user.setLoggedIn(false);
        else throw new UnableToLogOutException("you have logged out already");
        userRepository.save(user);
        return mapUserLogInResponse(user, logInResponse);
    }

    public SignInResponse signIn(SignInRequest signInRequest) {
        User user = new User();
        mapUserRequestToSignIn(signInRequest, user);
        if(isAValidUser(user)) userRepository.save(user);
        else throw new UsernameAlreadyExistsException("user already exist");
        return mapUserSignInResponse(user);
    }
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}

