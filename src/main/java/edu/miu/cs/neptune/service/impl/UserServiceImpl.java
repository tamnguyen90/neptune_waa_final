package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.core.service.impl.BaseServiceImpl;
import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.dto.UserDto;
import edu.miu.cs.neptune.dto.create.UserCreateDto;
import edu.miu.cs.neptune.dto.update.UserUpdateDto;
import edu.miu.cs.neptune.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserCreateDto, UserUpdateDto, UserDto, User, Long> {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public UserServiceImpl(JpaRepository<User, Long> repository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
    super(repository);
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public User fromCreateToEntity(UserCreateDto userCreateDto) {
    User entity = new User();

    // copy field from dto to entity

    return entity;
  }

  @Override
  public User fromUpdateToEntity(UserUpdateDto userUpdateDto) {
    User entity = new User();

    // copy field from dto to entity

    return entity;
  }

  @Override
  public UserDto fromEntityToDTO(User user) {
    UserDto dto = new UserDto();

    // copy field from entity to dto

    return dto;
  }


  public User saveUser(User user) {
    if (user.getPassword() != null) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    return userRepository.save(user);
  }
}
