package com.springboot.schulung.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.schulung.doaservice.UserDAOService;
import com.springboot.schulung.model.User;

/**
 * hier werden nur die resources erreicht. Aber die implementiereung darf hier hier nicht stehen
 * @author cedric
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private UserDAOService userDAOService;
	
	// findAll
	@GetMapping("/users")
	public List<User> retrieveUsers(){
		return userDAOService.findAll();
	}
	// query param 
	// localhost:8989/users?id=1
	
	// path param
	// local:8989/users/id
	// findById
	@GetMapping("/users/{id}")
	public User retrieveUserById(@PathParam("id") int id) throws Exception{
		User user = userDAOService.findById(id);
		if(user == null)
			throw new Exception("user not Found");
		return user;
	}
	// save/update
	// delete
	
}
