package com.techm.ms.resource;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techm.ms.exception.UserAlreadyExistException;
import com.techm.ms.exception.UserNotFoundException;
import com.techm.ms.model.User;
import com.techm.ms.service.UserService;

@RestController
public class UserResourceImpl implements UserResource {
	public static final Logger logger = LoggerFactory.getLogger(AccountResourceImpl.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work


	@Override
	public ResponseEntity<Object> crateNewUser(@RequestBody User user) {
		
		if(userService.isUserExist(user)) {
			throw new UserAlreadyExistException("id-"+ user);
		}
		
		User saveduser = userService.saveUser(user);		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saveduser.getId()).toUri();
			
			return ResponseEntity.created(location).build();
	}	
	
	@Override
	public User getUser(@PathVariable long id) {
		
		User user = userService.findByUserId(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+ id);
		}
		
		return user;
	}


}
