package com.word.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.model.Level;
import com.word.repository.LevelRepository;

@Service("levelDao")
@Transactional
public class LevelDaoImpl implements ILevelDao {

	private static final Logger logger = LogManager.getLogger(LevelDaoImpl.class);

	@Autowired
	private LevelRepository levelRepository;

	@Override
	public boolean save(Level level) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("save level begins. Level: ");
			sb.append(level.getLevel());
			logger.trace(sb.toString());
		}
		Level result = levelRepository.save(level);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("save level ends. ID: ");
			sb.append(result.getId());
			logger.trace(sb.toString());
		}
		return true;
	}

	@Override
	public boolean update(Level level) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("update level begins. ID: ");
			sb.append(level.getId());
			logger.trace(sb.toString());
		}
		Level result = levelRepository.save(level);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("update level ends. ID: ");
			sb.append(result.getId());
			logger.trace(sb.toString());
		}

		return true;
	}

	@Override
	public List<Level> getLevels() throws Exception {
		List<Level> levels = levelRepository.findAll();
		return levels;
	}

	@Override
	public Level getLevelByLevel(int level) throws Exception {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getLevelByLevel begins. Level: ");
			sb.append(level);
			logger.trace(sb.toString());
		}
		Level l = levelRepository.findByLevel(level);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getLevelByLevel ends. ID: ");
			sb.append(l.getId());
			logger.trace(sb.toString());
		}
		
		return l;
	}

	@Override
	public Level getLevelById(int id) throws Exception {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getLevelById begins. id: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		Level l = levelRepository.findOne(id);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getLevelById ends. ID: ");
			sb.append(l!=null?true:false);
			logger.trace(sb.toString());
		}
		
		return l;
	}


}
