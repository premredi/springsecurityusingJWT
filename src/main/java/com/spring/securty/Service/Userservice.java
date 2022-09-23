package com.spring.securty.Service;

import java.util.Optional;

import com.spring.securty.Model.UserInformation;


public interface Userservice {
	
public Integer savedetails(UserInformation user);
Optional<UserInformation> findbyusername(String username);
}
