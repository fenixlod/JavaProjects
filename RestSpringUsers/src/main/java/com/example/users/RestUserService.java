package com.example.users;

import java.util.List;

public interface RestUserService {
	RestUser getUserById(int id);
	List<RestUser> getAllUsers();
	void updateUser(int id,RestUser user);
	void deleteUser(int id);
	void deleteAllUsers();
	void addUser(RestUser user);
}
