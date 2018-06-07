package com.word.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.model.Level;
import com.word.model.Word;
import com.word.repository.WordRepository;

@Service("wordDao")
@Transactional
public class WordDaoImpl implements IWordDao {

	private static final Logger logger = LogManager.getLogger(WordDaoImpl.class);

	@Autowired
	private WordRepository wordRepository;
	
	@Autowired
	private ILevelDao levelDao;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean save(Word word) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("save word begins. Word: ");
			sb.append(word.getName());
			logger.trace(sb.toString());
		}
		Word result = wordRepository.save(word);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("save word ends. ID: ");
			sb.append(result.getId());
			logger.trace(sb.toString());
		}
		return true;
	}

	@Override
	public boolean update(Word word) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("update word begins. ID: ");
			sb.append(word.getId());
			logger.trace(sb.toString());
		}
		Word result = wordRepository.save(word);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("update word ends. ID: ");
			sb.append(result.getId());
			logger.trace(sb.toString());
		}

		return true;
	}

	@Override
	public List<Word> getWordsByLevel(int level) throws Exception {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getWordsByLevel begins. id: ");
			sb.append(level);
			logger.trace(sb.toString());
		}
		
		Level l = levelDao.getLevelByLevel(level);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getWordsByLevel ends. Level Id: ");
			sb.append(l.getId()).append(" Word size:" + l.getWords() != null ? l.getWords().size() : 0);
			logger.trace(sb.toString());
		}
		
		List<Word> words = null;
		
		if (l != null) {
			words =  l.getWords();
		}
		
		return words;
	}

	@Override
	public Word getWordByName(String name) throws Exception {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getWordByName begins. word: ");
			sb.append(name);
			logger.trace(sb.toString());
		}
		
		Word l = wordRepository.findByName(name);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getWordByName ends. id: ");
			sb.append(l.getId());
			logger.trace(sb.toString());
		}
		
		return l;
	}

	@Override
	public Word getWordsById(Integer id) throws Exception {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getWordsById begins. id: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		Word l = wordRepository.findOne(id);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getWordsById ends. id: ");
			sb.append(l.getId());
			logger.trace(sb.toString());
		}
		
		return l;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("delete begins. id: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		wordRepository.delete(id);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("delete ends. id: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		return true;
	}

	@Override
	public Integer saveWordLevel(Integer levelId, Integer wordId) throws Exception {
		
		Integer id;
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("save word begins. wordId: ");
			sb.append(wordId);
			sb.append("Level id:").append(levelId);
			logger.trace(sb.toString());
		}
		
		String sql = "insert into word_level(level_id,word_id) values (" + levelId + "," + wordId + ")";
		try {
			// s = new
			// String(Files.readAllBytes(Paths.get("src/main/resources/insert.sql")));
		id = em.createNativeQuery(sql).executeUpdate();
		
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		}
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("save word ends.: ");
			logger.trace(sb.toString());
		}
		return id;
	}
	

}
