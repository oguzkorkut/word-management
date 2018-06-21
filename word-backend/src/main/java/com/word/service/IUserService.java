package com.word.service;

import java.util.List;

import com.word.dto.RoleDto;
import com.word.dto.UserDto;

public interface IUserService {

	public UserDto getUserByUsername(String userName);

	public List<UserDto> getAllUsers();

	public UserDto saveUser(UserDto userDto);

	public boolean deleteById(Integer id);
	
	public RoleDto saveRole(RoleDto roleDto);
	
	public RoleDto getRoleByRoleName(String name);
	
	public List<RoleDto> getRoles();
	
	public boolean deleteRoleById(Integer id);
	
}
