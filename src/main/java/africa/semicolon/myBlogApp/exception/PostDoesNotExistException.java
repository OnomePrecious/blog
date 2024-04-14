package africa.semicolon.myBlogApp.exception;

public class PostDoesNotExistException extends PostNotAccessibleException{
    public PostDoesNotExistException(String message){
        super(message);
    }
}
