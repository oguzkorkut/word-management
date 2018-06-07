package com.word.dao;

import java.util.List;

import com.word.model.Word;

public interface IWordDao {
	public List<Word> getWordsByLevel(int level) throws Exception;
	
	public Word getWordsById(Integer id) throws Exception;

	public boolean save(Word word) throws Exception;
	
	public boolean delete(Integer id) throws Exception;

	public Word getWordByName(String name) throws Exception;
	
	public boolean update(Word word) throws Exception;

	public Integer saveWordLevel(Integer levelId, Integer wordId) throws Exception;
}
