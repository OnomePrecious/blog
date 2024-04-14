package africa.semicolon.myBlogApp.exception;

public class UnableToCreateAPostException extends PostNotAccessibleException{
    public UnableToCreateAPostException(String message){
        super(message);
    }
}
