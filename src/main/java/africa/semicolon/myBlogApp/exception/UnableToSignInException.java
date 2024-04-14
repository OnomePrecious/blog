package africa.semicolon.myBlogApp.exception;

public class UnableToSignInException extends PostNotAccessibleException{
    public UnableToSignInException(String message){
        super(message);
    }
}
