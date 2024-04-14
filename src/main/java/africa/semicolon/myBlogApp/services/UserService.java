package africa.semicolon.myBlogApp.services;

import africa.semicolon.myBlogApp.data.models.User;
import africa.semicolon.myBlogApp.dto.LogInRequest;
import africa.semicolon.myBlogApp.dto.LogInResponse;
import africa.semicolon.myBlogApp.dto.SignInRequest;
import africa.semicolon.myBlogApp.dto.SignInResponse;

public interface UserService  {
    LogInResponse logIn(LogInRequest logInRequest);
    LogInResponse logOut(LogInRequest logInRequest);
    SignInResponse signIn(SignInRequest signInRequest);
    User findUserByUsername(String username);

}
