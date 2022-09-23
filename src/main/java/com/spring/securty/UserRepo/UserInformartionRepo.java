package com.spring.securty.UserRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.securty.Model.UserInformation;

public interface UserInformartionRepo extends JpaRepository<UserInformation, Integer> {
	Optional<UserInformation> findbyusername(String username);

}
