package com.springboot.schulung.controller;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.schulung.doaservice.UserDAOService;
import com.springboot.schulung.exception.UserNotFoundException;
import com.springboot.schulung.model.User;

/**
 * hier werden nur die resources erreicht. Aber die implementiereung darf hier hier nicht stehen
 * @author cedric
 *
 */
@RestController
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserController.class.getSimpleName());
	@Autowired
	private UserDAOService userDAOService;
	
	// findAll
	@GetMapping("/users")
	public List<User> retrieveUsers(){
		return userDAOService.findAll();
	}
	
	// findById
	@GetMapping("/users/{id}")
	public User retrieveUserById(@PathVariable("id") int id) throws Exception{
		User user = userDAOService.findById(id);
		if(user == null)
			throw new UserNotFoundException("User not Found");
		return user;
	}

	// save/update
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@RequestBody User user) throws Exception {
		userDAOService.save(user);
		// LIEFERE WEITERE INFORMATIONEN
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
		
		/*if(user.getId() == null) {
			LOG.severe(" user: "+user.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // SERVER other DatenBak ERROR
			
			//throw new Exception("User could not be saved");
		}
		
		return ResponseEntity.ok(user).status(HttpStatus.CREATED).build();*/
	}
	
	//update 
	@PutMapping("/users")
	public void update(@RequestBody User user) {
		userDAOService.update(user);
	}
	
	// delete
	@DeleteMapping("/users/{id}")
	public void deleUser(@PathVariable("id") int id) throws Exception {
		User user = userDAOService.findById(id);
		if(user != null)
			userDAOService.deleteById(user);
		else
			throw new Exception("User not found");
	}
	
}
