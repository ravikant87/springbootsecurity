package com.marlabs.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marlabs.model.User;
import com.marlabs.rep.UserRepository;
import com.marlabs.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Integer saveUser(User user) {
		String pwd = user.getUserPwd();
		pwd = encoder.encode(pwd);
		user.setUserPwd(pwd);
		return repo.save(user).getId();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUserEmail(username);
		List<String> roles = user.getRoles();

		Set<GrantedAuthority> autority = new HashSet<>();

		for (String role : roles) {
			autority.add(new SimpleGrantedAuthority(role));
		}
		org.springframework.security.core.userdetails.User uob = new org.springframework.security.core.userdetails.User(
				username, user.getUserPwd(), autority);
		return uob;
	}
}
