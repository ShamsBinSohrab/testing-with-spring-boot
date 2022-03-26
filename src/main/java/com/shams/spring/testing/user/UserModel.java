package com.shams.spring.testing.user;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
public class UserModel {

  private int id;

  @Size(min = 1, max = 255)
  private String name;

  @Size(min = 1, max = 50)
  private String email;

  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}
