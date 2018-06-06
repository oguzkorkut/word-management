/**
 * 
 */
package com.word.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.word.model.Answer;
import com.word.model.Word;

@Repository
@Transactional
public interface WordRepository extends JpaRepository<Word, Integer>, JpaSpecificationExecutor<Answer> {

	
}
