package africa.semicolon.myBlogApp.exception;

public class PostNotAccessibleException extends RuntimeException{
    public PostNotAccessibleException(String message){
        super(message);
    }
}
