package com.word.service;

import java.util.List;

import com.word.dto.LevelDto;
import com.word.dto.WordDto;

public interface ILevelSerice {
	
	public boolean save(LevelDto levelDto) throws Exception;
	
	public boolean update(LevelDto levelDto) throws Exception;
	
	public List<LevelDto> getLevels() throws Exception;
	
	public List<WordDto> getWordsByLevelId(int id) throws Exception;
	
	public LevelDto getLevelByLevel(int level) throws Exception;
	
	public boolean save(WordDto wordDto) throws Exception;
	
	public WordDto getWordByName(String name) throws Exception;
	

}
