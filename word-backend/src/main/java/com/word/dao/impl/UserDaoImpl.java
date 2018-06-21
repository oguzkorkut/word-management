package com.word.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.dao.IUserDao;
import com.word.model.Role;
import com.word.model.User;
import com.word.repository.RoleRepository;
import com.word.repository.UserRepository;

@Service("userDao")
@Transactional
public class UserDaoImpl implements IUserDao {

	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User getUserByUsername(String userName) {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getUserByUsername begins. userName: ");
			sb.append(userName);
			logger.trace(sb.toString());
		}
		
		User user = userRepository.findByUsername(userName);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getUserByUsername ends. id: ");
			sb.append(user.getId());
			logger.trace(sb.toString());
		}
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getAllUsers begins.");
			logger.trace(sb.toString());
		}
		
		List<User> u = userRepository.findAll();

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getAllUsers ends. User size:" + u != null ? u.size() : 0);
			logger.trace(sb.toString());
		}
		
		return u;
	}

	@Override
	public User saveUser(User user) {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveUser begins. UserName: ");
			sb.append(user.getUsername());
			logger.trace(sb.toString());
		}
		User result = userRepository.save(user);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveUser ends. ID: ");
			sb.append(result.getId());
			logger.trace(sb.toString());
		}
		return result;
	}

	@Override
	public boolean deleteById(Integer id) {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("deleteById begins. id: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		userRepository.delete(id);
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("deleteById ends. id: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		return true;
	}

	@Override
	public List<Role> getRoles() {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getRoles begins.");
			logger.trace(sb.toString());
		}
		
		List<Role> roles = roleRepository.findAll();

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getRoles ends. Roles size:" + roles != null ? roles.size() : 0);
			logger.trace(sb.toString());
		}
		
		return roles;
	}

	@Override
	public boolean deleteRoleById(Integer id) {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("deleteRoleById begins. id: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		roleRepository.delete(id);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("deleteRoleById ends. id: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		return true;
	}

	@Override
	public Role saveRole(Role role) {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveRole begins. Name: ");
			sb.append(role.getName());
			logger.trace(sb.toString());
		}
		Role result = roleRepository.save(role);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveRole ends. ID: ");
			sb.append(result.getId());
			logger.trace(sb.toString());
		}
		return result;
	}

	@Override
	public Role getRoleByRoleName(String name) {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getRoleByRoleName begins. name: ");
			sb.append(name);
			logger.trace(sb.toString());
		}
		
		Role role = roleRepository.findByName(name);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getRoleByRoleName ends. id: ");
			sb.append(role.getId());
			logger.trace(sb.toString());
		}
		
		return role;
	}

}
