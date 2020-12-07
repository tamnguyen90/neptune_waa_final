package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
  User saveUser(User user);

  List<User> getAll();

  Optional<User> getById(Long userId);

  Optional<User> getByName(String userName);



//  void update(User user);
//
//  void inactive(String username);
}
