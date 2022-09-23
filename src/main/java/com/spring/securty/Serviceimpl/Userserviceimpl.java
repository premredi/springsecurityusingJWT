package com.spring.securty.Serviceimpl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.securty.Model.UserInformation;
import com.spring.securty.Service.Userservice;
import com.spring.securty.UserRepo.UserInformartionRepo;

@Service
public class Userserviceimpl implements Userservice,UserDetailsService{

	@Autowired
	UserInformartionRepo repo;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public Integer savedetails(UserInformation user) {
		user.setPassword(
				encoder.encode(user.getPassword())
				);
		return repo.save(user).getId();
	}
	public	Optional<UserInformation> findbyusername(String username){
		return repo.findbyusername(username);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInformation> opt=findbyusername(username);
		if(opt.isEmpty())
			throw new UsernameNotFoundException("user doen't exist");
		UserInformation user=opt.get();

		return new org.springframework.security.core.userdetails.User(username,user.getPassword(),
				user.getRoles().stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
	}
}
