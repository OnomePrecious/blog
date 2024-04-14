package africa.semicolon.myBlogApp.data.repository;

import africa.semicolon.myBlogApp.data.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findCommentByPostCommentedOn(String postCommentedOn);

    Comment findCommentByComment(String comment);
}
