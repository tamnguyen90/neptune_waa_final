package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.dto.UserDto;
import edu.miu.cs.neptune.repository.UserRepository;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public User saveUser(User user) {
    if (user.getPassword() != null) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    return userRepository.save(user);
  }

  @Override
  public List<User> getAll() {
    return null;
  }

  @Override
  public Optional<User> getById(String username) {
    return Optional.empty();
  }

  @Override
  public void update(UserDto user) {

  }

  @Override
  public void delete(String username) {

  }


}
