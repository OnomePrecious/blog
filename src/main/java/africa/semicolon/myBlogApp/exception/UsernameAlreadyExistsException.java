package africa.semicolon.myBlogApp.exception;

public class UsernameAlreadyExistsException extends PostNotAccessibleException{
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
