package com.shams.spring.testing.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findById(int id);
}
