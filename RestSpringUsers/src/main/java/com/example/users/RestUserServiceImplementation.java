package com.example.users;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.*;

@Service("RestUserService")
//@Transactional
public class RestUserServiceImplementation implements RestUserService {

	List<RestUser> allUsers = new ArrayList<RestUser>();
	
	@Override
	public RestUser getUserById(int id) {
		for(RestUser user : allUsers) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}

	@Override
	public List<RestUser> getAllUsers() {
		return allUsers;
	}

	@Override
	public void updateUser(int id,RestUser user) {
		RestUser findUser = getUserById(id);
		if(findUser == null)
			return;
		
		findUser.setName(user.getName());
	}

	@Override
	public void deleteUser(int id) {
		RestUser user = getUserById(id);
		
		if(user != null)
			allUsers.remove(user);
	}

	@Override
	public void deleteAllUsers() {
		allUsers.clear();
	}
	
	@Override
	public void addUser(RestUser user) {
		if(getUserById(user.getId()) != null)
			return;
		
		allUsers.add(user);
	}
}
