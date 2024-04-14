package africa.semicolon.myBlogApp.exception;

public class UnableToLogInException extends PostNotAccessibleException{
    public UnableToLogInException(String message){
        super(message);
    }
}
