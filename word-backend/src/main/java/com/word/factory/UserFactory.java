package com.word.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.word.dto.RoleDto;
import com.word.dto.UserDto;
import com.word.model.Role;
import com.word.model.User;

@Service("userFactory")
public class UserFactory {

	private static final Logger logger = LogManager.getLogger(UserFactory.class);
	
	public UserDto convertUserToUserDto(User user) {
		if (user == null) {
			logger.error("convertUserToUserDto -> user is null");
			return null;
		}
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(user, userDto);

		if (!CollectionUtils.isEmpty(user.getRoles())) {

			RoleDto roleDto = null;
			
			List<Role> roles = new ArrayList<Role>(user.getRoles());

			for (Role role : roles) {

				roleDto = new RoleDto();

				BeanUtils.copyProperties(role, roleDto);
				
				userDto.getRoles().add(roleDto);
			}
		}

		return userDto;
	}

	public User convertUserDtoToUser(UserDto userDto) {
		if (userDto == null) {
			logger.error("convertUserDtoToUser -> userDto is null");
			return null;
		}
		User user = new User();

		BeanUtils.copyProperties(userDto, user);

		if (!CollectionUtils.isEmpty(userDto.getRoles())) {

			Role role = null;
			
			List<RoleDto> roleDtos = userDto.getRoles();

			for (RoleDto roleDto : roleDtos) {

				role = new Role();

				BeanUtils.copyProperties(roleDto, role);
				
				user.getRoles().add(role);
			}
		}

		return user;
	}
	
	public RoleDto convertRoleToRoleDto(Role role) {
		if (role == null) {
			logger.error("convertRoleToRoleDto -> role is null");
			return null;
		}
		RoleDto roleDto = new RoleDto();

		BeanUtils.copyProperties(role, roleDto);

		return roleDto;
	}
	
	public Role convertRoleDtoToRole(RoleDto roleDto) {
		if (roleDto == null) {
			logger.error("convertRoleDtoToRole -> role is null");
			return null;
		}
		Role role = new Role();

		BeanUtils.copyProperties(roleDto, role);

		return role;
	}
}
