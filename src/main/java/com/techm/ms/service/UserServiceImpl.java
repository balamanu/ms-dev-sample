package com.techm.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.ms.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static List<User> users;

	static {
		users = populateDummyUsers();
	}
	
	public List<User> findAllUsers() {
		return users;
	}
	
	public User saveUser(User user) {
		users.add(user);
		return user;
	}
	
	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	public User findByUserId(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(1, "Bala", 31, 123));
		users.add(new User(2, "krishna", 32, 456));
		users.add(new User(3, "manukonda", 33, 789));
		
		return users;
		

	}

}
