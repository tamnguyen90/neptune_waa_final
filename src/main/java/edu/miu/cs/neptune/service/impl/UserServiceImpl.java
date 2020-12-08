package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.constant.Constant;
import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.domain.UserVerification;
import edu.miu.cs.neptune.repository.UserRepository;
import edu.miu.cs.neptune.service.GenerateService;
import edu.miu.cs.neptune.service.MailService;
import edu.miu.cs.neptune.service.UserService;
import edu.miu.cs.neptune.service.UserVerificationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final MailService mailService;
  private final GenerateService generateService;
  private final UserVerificationService userVerificationService;

  public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                         MailService mailService, GenerateService generateService,
                         UserVerificationService userVerificationService) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.mailService = mailService;
    this.generateService = generateService;
    this.userVerificationService = userVerificationService;
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
    String mailContent = "Please use this verification code: "+verificationCode+" to verify your account.";
    mailService.sendEmail(mailFrom,mailTo,mailSubject,mailContent);

    User userNew = userRepository.save(user);
    userRepository.flush();

    UserVerification userVerification = new UserVerification();
    userVerification.setUserId(userNew.getUserId());
    userVerification.setVerificationCode(verificationCode);
    userVerification.setEndingTime(LocalDateTime.now().plusMinutes(Constant.EXPIRE_MINUTE));

    userVerificationService.save(userVerification);
    return userNew;
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> getById(Long userId) {
    return userRepository.findById(userId);
  }

  @Override
  public Optional<User> getByName(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public Boolean userAuthentication(String username, String password){
      if(userRepository.findByUsername(username)!=null){
        User user = userRepository.findByUsername(username).get();
        if(user.getPassword()==passwordEncoder.encode(password)){
          return true;
        }
        else return false;
      }
      return false;
}



//  @Override
//  public void update(User user) {
//  }

//  @Override
//  public void inactive(String username) {
//  }

}
