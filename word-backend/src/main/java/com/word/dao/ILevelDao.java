package com.word.dao;

import java.util.List;

import com.word.model.Level;

public interface ILevelDao {
public boolean save(Level level) throws Exception;
	
	public boolean update(Level level) throws Exception;
	
	public List<Level> getLevels() throws Exception;
	
	public Level getLevelByLevel(int level) throws Exception;
	
	public Level getLevelById(int id) throws Exception;
	
}
