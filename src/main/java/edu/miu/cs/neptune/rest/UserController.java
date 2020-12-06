package edu.miu.cs.neptune.rest;

import edu.miu.cs.neptune.core.rest.impl.BaseControllerImpl;
import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.dto.UserDto;
import edu.miu.cs.neptune.dto.create.UserCreateDto;
import edu.miu.cs.neptune.dto.update.UserUpdateDto;
import edu.miu.cs.neptune.service.UserService;
import edu.miu.cs.neptune.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseControllerImpl<UserCreateDto, UserUpdateDto, UserDto, User, Long> {

  @Autowired
  public UserController(UserServiceImpl userService) {
    super(userService);
  }

}
