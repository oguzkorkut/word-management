package com.word.dao;

import java.util.List;

import com.word.model.Level;
import com.word.model.Word;

public interface ILevelDao {
public boolean save(Level level) throws Exception;
	
	public boolean update(Level level) throws Exception;
	
	public List<Level> getLevels() throws Exception;
	
	public List<Word> getWordsByLevelId(int id) throws Exception;
	
	public Level getLevelByLevel(int level) throws Exception;
	
	public boolean save(Word word) throws Exception;
	
	public Word getWordByName(String name) throws Exception;
}
