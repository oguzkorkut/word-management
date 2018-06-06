package com.word.dao;

import com.word.model.Answer;

public interface IAnswerDao {

	public boolean save(Answer answer)  throws Exception;
	
	public boolean update(Answer answer)  throws Exception;
	
	public boolean delete(Integer id)  throws Exception;
	
}
