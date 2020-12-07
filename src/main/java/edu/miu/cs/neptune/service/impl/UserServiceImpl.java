package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.repository.UserRepository;
import edu.miu.cs.neptune.service.GenerateService;
import edu.miu.cs.neptune.service.MailService;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final MailService mailService;
  private final GenerateService generateService;

  public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                         MailService mailService, GenerateService generateService) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.mailService = mailService;
    this.generateService = generateService;
  }

  @Override
  public User saveUser(User user) {
    if (user.getPassword() != null) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    String mailTo = user.getEmail();
    String mailFrom = "asdproject287@gmail.com";
    String mailSubject = "New Account notification";

    String verificationCode = generateService.generateCode();
    String mailContent = "Please use this verification code: " + verificationCode;
    mailService.sendEmail(mailFrom,mailTo,mailSubject,mailContent);
    return userRepository.save(user);
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> getById(Long userId) {
    return userRepository.findById(userId);
  }

//  @Override
//  public void update(User user) {
//  }

//  @Override
//  public void inactive(String username) {
//  }

}
