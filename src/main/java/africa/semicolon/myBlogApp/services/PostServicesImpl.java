package africa.semicolon.myBlogApp.services;
import africa.semicolon.myBlogApp.data.models.Post;
import africa.semicolon.myBlogApp.data.repository.PostRepository;
import africa.semicolon.myBlogApp.data.repository.UserRepository;
import africa.semicolon.myBlogApp.dto.PostRequest;
import africa.semicolon.myBlogApp.dto.PostResponse;
import africa.semicolon.myBlogApp.dto.UpdatePostRequest;
import africa.semicolon.myBlogApp.exception.PostDoesNotExistException;
import africa.semicolon.myBlogApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static africa.semicolon.myBlogApp.util.utils.Mappers.*;


@Service
public class PostServicesImpl implements PostServices{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostResponse createAPost(PostRequest postRequest) {
        Post post = new Post();
        var user = userRepository.findUserByUsername(postRequest.getUsername());
        if(user==null)throw new UserNotFoundException("You have to be a registered user");
        if(!user.isLoggedIn())throw new UserNotFoundException("You have to log in first");
        mapUserPostRequest(postRequest, post);
        postRepository.save(post);
        userRepository.save(user);
        return mapUserPostToResponse(post);
    }

    @Override
    public PostResponse deletePostBy(PostRequest postRequest) {
        var user = userRepository.findUserByUsername(postRequest.getUsername());
        var post = postRepository.findPostByContent(postRequest.getContent());
        if(post == null) throw new PostDoesNotExistException("no post available");
        if(!user.isLoggedIn()) throw new UserNotFoundException("You have to log in");
        postRepository.deleteById(post.getId());
        postRepository.delete(post);
        user.setPosts(postRepository.findPostByPosterUsername(post.getPosterUsername()));
        userRepository.save(user);
        return mapUserPostToResponse(post);
    }

    @Override
    public PostResponse updatePost(UpdatePostRequest updatePostRequest) {
        var post = postRepository.findPostByContent(updatePostRequest.getContent());
        if(post == null) throw new PostDoesNotExistException("no post available");
        mapUpdateRequestToPost(updatePostRequest,post);
        postRepository.save(post);
        var user = userRepository.findUserByUsername(updatePostRequest.getUsername());
        if(!user.isLoggedIn()) throw new UserNotFoundException("You have to log in first");
        user.setPosts(postRepository.findPostByPosterUsername(updatePostRequest.getUsername()));
        userRepository.save(user);
            return mapUserPostToResponse(post);
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }
}
