package com.word.dao;

import java.util.List;

import com.word.model.Role;
import com.word.model.User;

public interface IUserDao {

	public User getUserByUsername(String userName);

	public List<User> getAllUsers();

	public User saveUser(User user);

	public boolean deleteById(Integer id);// UUID

	public List<Role> getRoles();

	public boolean deleteRoleById(Integer id);

	public Role saveRole(Role role);

	public Role getRoleByRoleName(String name);

}
