package com.tobeto.java4a.pair4lms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobeto.java4a.pair4lms.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
}
