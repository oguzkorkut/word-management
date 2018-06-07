package com.word.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.word.dao.ILevelDao;
import com.word.dto.LevelDto;
import com.word.dto.WordDto;
import com.word.factory.WordFactory;
import com.word.model.Level;

@Service("levelSerice")
@Transactional
public class LevelSericeImpl implements ILevelSerice {

	private static final Logger logger = LogManager.getLogger(LevelSericeImpl.class);
	
	@Autowired
	private WordFactory wordFactory;
	
	@Autowired
	private ILevelDao levelDao;

	@Override
	public boolean save(LevelDto levelDto) throws Exception {
		
		if (levelDto == null) {
			logger.error("save level -> levelDto is null");
			return false;
		}
		
		Level level = wordFactory.convertLevelDtoToLevel(levelDto);
		
		boolean control = levelDao.save(level);
		
		return control;
	}

	@Override
	public boolean update(LevelDto levelDto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LevelDto> getLevels() throws Exception {

		List<Level> levels = levelDao.getLevels();
		
		if (CollectionUtils.isEmpty(levels)) {
			return new ArrayList<LevelDto>();
		}
		
		List<LevelDto> levelDtos = new ArrayList<LevelDto>();
		
		LevelDto levelDto = null;
				
		for (Level level : levels) {
			levelDto = wordFactory.convertLevelToLevelDto(level);
			
			levelDtos.add(levelDto);
		}
		
		return levelDtos;
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
