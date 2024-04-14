package africa.semicolon.myBlogApp.services;

import africa.semicolon.myBlogApp.data.models.Comment;
import africa.semicolon.myBlogApp.data.models.Post;
import africa.semicolon.myBlogApp.data.models.User;
import africa.semicolon.myBlogApp.data.repository.CommentRepository;
import africa.semicolon.myBlogApp.data.repository.PostRepository;
import africa.semicolon.myBlogApp.data.repository.UserRepository;
import africa.semicolon.myBlogApp.dto.CommentRequest;
import africa.semicolon.myBlogApp.dto.CommentResponse;
import africa.semicolon.myBlogApp.exception.PostDoesNotExistException;
import africa.semicolon.myBlogApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.myBlogApp.util.utils.Mappers.mapCommentRequestToComment;
import static africa.semicolon.myBlogApp.util.utils.Mappers.mapCommentRequestToResponse;


@Service
public class CommentServicesImpl implements CommentServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentResponse commentOnAPost(CommentRequest commentRequest) {
        Comment comment = new Comment();
        User commenter = userRepository.findUserByUsername(commentRequest.getCommenterUsername());
        Post post = postRepository.findPostByContent(commentRequest.getPostContent());
        User postOwner = userRepository.findUserByUsername(post.getPosterUsername());
        comment.setCommenter(commenter);
        mapCommentRequestToComment(commentRequest, comment);
        commentRepository.save(comment);
        post.setComments(commentRepository.findCommentByPostCommentedOn(comment.getPostCommentedOn()));
        postRepository.save(post);
        postOwner.setPosts(postRepository.findPostByPosterUsername(postOwner.getUsername()));
        userRepository.save(postOwner);
        return mapCommentRequestToResponse(commentRequest);
    }

    @Override
    public CommentResponse deleteCommentByRequest(CommentRequest commentRequest) {
        Post post = postRepository.findPostByContent(commentRequest.getPostContent());
        if (post == null) throw new PostDoesNotExistException("no post created");
        var user = userRepository.findUserByUsername(post.getPosterUsername());
        Comment comment = commentRepository.findCommentByComment(commentRequest.getComment());
        if (!user.isLoggedIn()) throw new UserNotFoundException("you have to log in first");
        commentRepository.deleteById(comment.getId());
        post.setComments(commentRepository.findCommentByPostCommentedOn(post.getContent()));
        postRepository.save(post);
        user.setPosts(postRepository.findPostByPosterUsername(post.getPosterUsername()));
        userRepository.save(user);
        return mapCommentRequestToResponse(commentRequest);
    }

}
