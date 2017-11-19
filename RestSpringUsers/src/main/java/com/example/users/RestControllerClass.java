package com.example.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class RestControllerClass {
	
	@Autowired
	RestUserService service;
	
	@RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
	public ResponseEntity<RestUser> getUserById(@PathVariable("id") int id) {
		RestUser user = service.getUserById(id);
		
		if(user == null)
			return new ResponseEntity<RestUser>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<RestUser>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public ResponseEntity<List<RestUser>> getAllUsers() {
		List<RestUser> users = service.getAllUsers();
		
		if(users == null)
			return new ResponseEntity<List<RestUser>>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<RestUser>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users",method = RequestMethod.POST)
	public void addUser(@RequestBody RestUser newUser) {
		service.addUser(newUser);
	}
	
	@RequestMapping(value = "/users/{id}",method = RequestMethod.PUT)
	public void updateUser(@PathVariable("id") int id,@RequestBody RestUser oldUser) {
		service.updateUser(id,oldUser);
	}
	
	@RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") int id) {
		service.deleteUser(id);
	}
	
	@RequestMapping(value = "/users",method = RequestMethod.DELETE)
	public void deleteAllUser() {
		service.deleteAllUsers();
	}
	
	@RequestMapping(value = "/users/init",method = RequestMethod.POST)
	public void initUsers() {
		RestUser user = new RestUser("Vladko",1,"admin");
		service.addUser(user);
	}
}
