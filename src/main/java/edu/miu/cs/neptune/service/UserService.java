package edu.miu.cs.neptune.service;


import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.dto.UserDto;

import java.util.List;
import java.util.Optional;
//
public interface UserService {
  User saveUser(User user);

  List<User> getAll();

  Optional<User> getById(String username);

  void update(UserDto user);

  void delete(String username);
}
