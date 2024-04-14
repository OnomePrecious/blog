package africa.semicolon.myBlogApp.data.repository;

import africa.semicolon.myBlogApp.data.models.View;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ViewRepository extends MongoRepository<View, String> {
}
