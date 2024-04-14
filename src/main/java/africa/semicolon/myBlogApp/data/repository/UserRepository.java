package africa.semicolon.myBlogApp.data.repository;

import africa.semicolon.myBlogApp.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}
