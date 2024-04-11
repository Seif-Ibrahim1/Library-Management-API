package com.example.librarymanagement.Services;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.librarymanagement.Entities.User;
import com.example.librarymanagement.Repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder bcryptEncoder;

	JwtUserDetailsService(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
		this.userRepository = userRepository;
		this.bcryptEncoder = bcryptEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public User save(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}

}