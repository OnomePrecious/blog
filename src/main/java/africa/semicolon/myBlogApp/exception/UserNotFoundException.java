package africa.semicolon.myBlogApp.exception;

public class UserNotFoundException extends PostNotAccessibleException{
    public UserNotFoundException(String message){
        super(message);
    }
}
