package com.marlabs.rep;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlabs.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	User findByUserEmail(String userEmail);
	
	

}
