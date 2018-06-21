/**
 * 
 */
package com.word.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.word.model.Role;
import com.word.model.User;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<User> {

	public Role findByName(String name);
}
