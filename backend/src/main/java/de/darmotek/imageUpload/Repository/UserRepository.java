package de.darmotek.imageUpload.Repository;

import de.darmotek.imageUpload.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);
}
