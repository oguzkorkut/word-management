package com.word.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.word.dao.impl.UserDaoImpl;
import com.word.dto.RoleDto;
import com.word.dto.UserDto;
import com.word.factory.UserFactory;
import com.word.model.Role;
import com.word.model.User;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDaoImpl userDao;
	
	@Autowired
	private UserFactory userFactory;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<UserDto> getAllUsers() {
		
		List<UserDto> userDtos = new ArrayList<UserDto>();
		
		List<User> users = userDao.getAllUsers();
		
		if (!CollectionUtils.isEmpty(users)) {
			
			UserDto userDto = null;
			
			for (User user : users) {
				userDto = userFactory.convertUserToUserDto(user);
				
				userDtos.add(userDto);
			}
		}
		
		return userDtos;
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
	
		UserDto tempUserDto = null;
		
		User user= userFactory.convertUserDtoToUser(userDto);
		
		if (user != null) {
			User tempUser =	userDao.saveUser(user);
			
			tempUserDto = userFactory.convertUserToUserDto(tempUser);
		}
		return tempUserDto;
	}

	@Override
	public UserDto getUserByUsername(String userName) {

		User user = userDao.getUserByUsername(userName);
		
		UserDto userDto = userFactory.convertUserToUserDto(user);
		
		return userDto;
	}

	@Override
	public boolean deleteById(Integer id) {
		return userDao.deleteById(id);
	}

	@Override
	public List<RoleDto> getRoles() {
		
		List<RoleDto> roleDtos =  new ArrayList<RoleDto>();
		
		List<Role> roles = userDao.getRoles();
		
		if (CollectionUtils.isEmpty(roles)) {
			logger.info("Roles is empty!");
			return roleDtos;
		}
		
		RoleDto roleDto = null;
		for (Role role : roles) {
			roleDto = userFactory.convertRoleToRoleDto(role);
			
			roleDtos.add(roleDto);
		}
		
		return roleDtos;
	}

	@Override
	public boolean deleteRoleById(Integer id) {
		return userDao.deleteRoleById(id);
	}

	@Override
	public RoleDto saveRole(RoleDto roleDto) {
		
		Role role = userFactory.convertRoleDtoToRole(roleDto);
		
		Role tempRole = userDao.saveRole(role);

		RoleDto tempRoleDto = userFactory.convertRoleToRoleDto(tempRole);
		
		return tempRoleDto;
	}

	@Override
	public RoleDto getRoleByRoleName(String name) {
		
		Role role = userDao.getRoleByRoleName(name);
		
		RoleDto roleDto = userFactory.convertRoleToRoleDto(role);
		
		return roleDto;
	}

}
