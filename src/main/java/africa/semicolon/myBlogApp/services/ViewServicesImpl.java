package africa.semicolon.myBlogApp.services;

import africa.semicolon.myBlogApp.data.models.View;
import africa.semicolon.myBlogApp.data.repository.PostRepository;
import africa.semicolon.myBlogApp.data.repository.UserRepository;
import africa.semicolon.myBlogApp.data.repository.ViewRepository;
import africa.semicolon.myBlogApp.dto.ViewRequest;
import africa.semicolon.myBlogApp.dto.ViewResponse;
import africa.semicolon.myBlogApp.exception.PostDoesNotExistException;
import africa.semicolon.myBlogApp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static africa.semicolon.myBlogApp.util.utils.Mappers.mapViewRequestToResponse;


@Service
public class ViewServicesImpl implements ViewServices{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ViewResponse viewPost(ViewRequest viewRequest) {
        View view = new View();
        var post = postRepository.findPostByContent(viewRequest.getContentToView());
       // view.setViewer(userRepository.findUserByUsername(viewRequest.getViewerUsername()));
        if(post == null) throw new PostDoesNotExistException("no post available");
        var user = userRepository.findUserByUsername(post.getPosterUsername());
        if(!user.isLoggedIn()) throw new UserNotFoundException("you have to log in");
        viewRequest.setTimeOfView(LocalDateTime.now());
        viewRequest.setContentToView(post.getContent());
        viewRequest.setViewerUsername(user.getUsername());
        viewRepository.save(view);
        userRepository.save(user);
        return mapViewRequestToResponse(viewRequest);
    }
}
