/**
 * 
 */
package com.word.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.word.model.Level;

@Repository
@Transactional
public interface LevelRepository extends JpaRepository<Level, Integer>, JpaSpecificationExecutor<Level> {

	Level findByLevel(int level);

}
