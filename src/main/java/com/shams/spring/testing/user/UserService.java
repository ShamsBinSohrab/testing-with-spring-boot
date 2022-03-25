package com.shams.spring.testing.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  public User getUser(int id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No user found with id: " + id));
  }

  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }
}
