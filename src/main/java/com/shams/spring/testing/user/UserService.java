package com.shams.spring.testing.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  public User get(int id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No user found with id: " + id));
  }

  @Transactional
  public User create(User user) {
    user.setCreatedAt(ZonedDateTime.now());
    return userRepository.save(user);
  }

  @Transactional
  public User update(User existingUser, User updatedUser) {
    existingUser.setName(updatedUser.getName());
    existingUser.setEmail(updatedUser.getEmail());
    existingUser.setUpdatedAt(ZonedDateTime.now());
    return userRepository.save(existingUser);
  }
}
