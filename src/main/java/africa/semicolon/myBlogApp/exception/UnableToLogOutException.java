package africa.semicolon.myBlogApp.exception;

public class UnableToLogOutException extends PostNotAccessibleException{
    public UnableToLogOutException(String message){
        super(message);
    }
}

