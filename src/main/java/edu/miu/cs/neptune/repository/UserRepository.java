package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
