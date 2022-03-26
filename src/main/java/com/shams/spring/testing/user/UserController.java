package com.shams.spring.testing.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  User create(@Valid @RequestBody User user) {
    return userService.create(user);
  }

  @GetMapping("/{id}")
  User get(@PathVariable int id) {
    return userService.get(id);
  }

  @PutMapping("/{id}")
  User update(@PathVariable int id, @Valid @RequestBody User user) {
    var existingUser = userService.get(id);
    return userService.update(existingUser, user);
  }
}
