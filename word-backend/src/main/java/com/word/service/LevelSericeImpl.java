package com.word.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.word.dto.LevelDto;
import com.word.dto.WordDto;

@Service("levelSerice")
@Transactional
public class LevelSericeImpl implements ILevelSerice {

	private static final Logger logger = LogManager.getLogger(LevelSericeImpl.class);

	@Override
	public boolean save(LevelDto levelDto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(LevelDto levelDto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LevelDto> getLevels() throws Exception {

		return null;
	}

	@Override
	public List<WordDto> getWordsByLevelId(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LevelDto getLevelByLevel(int level) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(WordDto wordDto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WordDto getWordByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
