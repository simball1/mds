package com.mysite.mds.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.mds.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser getUser(String username) {
	
		Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
		if(siteUser.isPresent()) {
			return siteUser.get();
		} else {
			throw new DataNotFoundException("siteuser not found");
		}
	}
	
	public SiteUser create(UserCreateForm userCreateForm) {

		SiteUser user = new SiteUser();
		user.setUsername(userCreateForm.getUsername());
		user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
		user.setEmail(userCreateForm.getEmail());
		user.setUserRole(UserRole.USER);
		this.userRepository.save(user);
		return user;
		
	}
}
