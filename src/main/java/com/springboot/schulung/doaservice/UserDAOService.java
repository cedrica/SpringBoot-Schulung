package com.springboot.schulung.doaservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.schulung.model.User;

@Component
public class UserDAOService  {

	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1, "Maurice", 33, new Date()));
		users.add(new User(2, "Ismaro", 35, new Date()));
		users.add(new User(3, "Cedric", 36, new Date()));
		users.add(new User(4, "Isa", 13, new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}

	public User findById(int id) {
		for (User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	

}
