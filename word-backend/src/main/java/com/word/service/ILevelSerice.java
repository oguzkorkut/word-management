package com.word.service;

import java.util.List;

import com.word.dto.LevelDto;

public interface ILevelSerice {
	
	public boolean save(LevelDto levelDto) throws Exception;
	
	public boolean update(LevelDto levelDto) throws Exception;
	
	public List<LevelDto> getLevels() throws Exception;
	
	public LevelDto getLevelByLevel(int level) throws Exception;
	
}
