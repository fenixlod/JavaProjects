package com.projecttwo.Users2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.projectone.Users1.User;

@RestController
public class Users2Controller {
	private Map<String,User> allUsers;
	
	public Users2Controller() {
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		allUsers = instance.getMap("Users");
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		List<User> allu = new ArrayList<User>();
		
		Collection<User> users = allUsers.values();
		for(User u : users) {
			allu.add(u);
		}
		
		return allu;
	}
	
	@RequestMapping(value = "/add", method =  RequestMethod.POST)
	public void addUser(@RequestParam(value = "id", defaultValue = "0") int id, @RequestParam(value = "name", defaultValue = "") String name, 
			@RequestParam(value = "type", defaultValue = "none") String type) {
		User u = new User();
		u.setName(name);
		u.setId(id);
		u.setType(type);
		allUsers.put(name, u);
	}
}
