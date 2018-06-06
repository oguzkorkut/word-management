package com.word.service;

import java.util.List;
import java.util.UUID;

import com.word.model.User;



public interface IUserService {

	public User findUserByUsername(String userName);

	public List<User> findAllUsers();

	public User saveUser(User user);

	public boolean deleteById(UUID id);
	
}
