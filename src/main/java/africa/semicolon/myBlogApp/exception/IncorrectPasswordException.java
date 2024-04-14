package africa.semicolon.myBlogApp.exception;

public class IncorrectPasswordException extends PostNotAccessibleException{
    public IncorrectPasswordException(String message){
        super(message);

    }

}
