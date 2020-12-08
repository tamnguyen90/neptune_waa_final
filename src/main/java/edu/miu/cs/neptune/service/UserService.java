package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.ProfileVerificationType;
import edu.miu.cs.neptune.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
  User saveUser(User user);

  List<User> getAll();

  Optional<User> getById(Long userId);

  //Get list all pending profile to be verified.
  List<User> findAllPendingProfile();

//  void update(User user);
//
//  void inactive(String username);
}
