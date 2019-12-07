package com.springboot.schulung.doaservice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.schulung.model.User;

@Component
public class UserDAOService  {

	// IN MEMORY (H2)
	private static List<User> users = new ArrayList<User>();
	private static int COUNTER = 0;
	static {
		users.add(new User(1, "Maurice", 33, new Date()));
		users.add(new User(2, "Ismaro", 35, new Date()));
		users.add(new User(3, "Cedric", 36, new Date()));
		users.add(new User(4, "Isa", 13, new Date()));
	}
	
	public List<User> findAll() {
		return users.stream().sorted(new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.getId() - o2.getId();
			}
		}).collect(Collectors.toList());
	}

	public User findById(int id) {
		for (User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public void save(User user) {
		COUNTER = users.size();
		//user.setId(++COUNTER);
		//users.add(user);
	}

	public void update(User user) {
		boolean isUserExist = false;
		for (User us : users) {
			if(user.getId() == us.getId()) {
				users.remove(us);
				users.add(user);
				isUserExist = true;
				break;
			}
		}
		if(!isUserExist) {
			save(user);
		}
	}

	public void deleteById(User user) {
		users.remove(user);
	}
	
	

}
