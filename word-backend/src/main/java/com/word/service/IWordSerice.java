package com.word.service;

import java.util.List;

import com.word.dto.WordDto;

public interface IWordSerice {
	
	public List<WordDto> getWordsByLevel(int level) throws Exception;
	
	public Integer save(int level, WordDto wordDto) throws Exception;
	
	public WordDto getWordByName(String name) throws Exception;
	
	public WordDto getWordsById(Integer id) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public boolean update(WordDto wordDto) throws Exception;
	
	public Integer saveWordLevel(Integer levelId,Integer wordId) throws Exception;
	

}
