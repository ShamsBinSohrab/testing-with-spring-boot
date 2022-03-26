package com.shams.spring.testing.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private final User user = new User();

  @InjectMocks private UserService userService;
  @Mock private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    user.setId(12);
    user.setName("Tester");
    user.setEmail("tester@work.com");
  }

  @Test
  @DisplayName("Should be able to get an user by id 12")
  void getUser() {
    when(userRepository.findById(eq(12))).thenReturn(Optional.of(user));
    var thisUser = userService.get(12);

    assertThat(thisUser.getId()).isEqualTo(12);
    assertThat(thisUser.getName()).isEqualTo("Tester");
    assertThat(thisUser.getEmail()).isEqualTo("tester@work.com");
  }

  @Test
  @DisplayName("Should not be able to get an user by id 15 rather throw an EntityNotFoundException")
  void userNotFound() {
    var exception = assertThrows(EntityNotFoundException.class, () -> userService.get(15));
    assertThat(exception.getMessage()).isEqualTo("No user found with id: 15");
  }

  @Test
  @DisplayName(
      "Should create a new user and set the created at filed, the updated at field should be null")
  void createUser() {
    userService.create(user);

    var argumentCaptor = ArgumentCaptor.forClass(User.class);
    verify(userRepository, times(1)).save(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue().getName()).isEqualTo("Tester");
    assertThat(argumentCaptor.getValue().getEmail()).isEqualTo("tester@work.com");
    assertThat(argumentCaptor.getValue().getCreatedAt()).isNotNull();
    assertThat(argumentCaptor.getValue().getUpdatedAt()).isNull();
  }

  @Test
  @DisplayName("Should update an existing user and set the updated at field")
  void updateUser() {
    var updatedUser = new User();
    updatedUser.setName("New tester");
    updatedUser.setEmail("new-tester@work.com");

    userService.update(user, updatedUser);

    var argumentCaptor = ArgumentCaptor.forClass(User.class);
    verify(userRepository, times(1)).save(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue().getName()).isEqualTo("New tester");
    assertThat(argumentCaptor.getValue().getEmail()).isEqualTo("new-tester@work.com");
    assertThat(argumentCaptor.getValue().getUpdatedAt()).isNotNull();
  }
}
