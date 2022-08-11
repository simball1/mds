package com.mysite.mds.domain;

import com.mysite.mds.user.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
	
	private String username;
	
	private String password;
	
	private String email;
	
	private UserRole userRole;
}
