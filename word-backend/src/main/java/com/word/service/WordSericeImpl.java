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
import com.word.dao.IWordDao;
import com.word.dto.WordDto;
import com.word.factory.WordFactory;
import com.word.model.Level;
import com.word.model.Word;

@Service("wordSerice")
@Transactional
public class WordSericeImpl implements IWordSerice {

	private static final Logger logger = LogManager.getLogger(WordSericeImpl.class);
	
	@Autowired
	private WordFactory wordFactory;
	
	@Autowired
	private IWordDao wordDao;
	
	@Autowired
	private ILevelDao levelDao; 
	
	@Override
	public List<WordDto> getWordsByLevel(int level) throws Exception {
		
		List<Word> words =wordDao.getWordsByLevel(level);
		
		if (CollectionUtils.isEmpty(words)) {
			return new ArrayList<WordDto>();
		}
		
		List<WordDto>  wordDtos = new ArrayList<WordDto>();
  		
		WordDto wordDto = null;
		for (Word word : words) {
			
			wordDto = wordFactory.convertWordToWordDto(word);
			
			wordDtos.add(wordDto);
		}
		
		return wordDtos;
	}

	@Override
	public Integer save(int level, WordDto wordDto) throws Exception {
		
		if (wordDto == null) {
			return 0;
		}
		Word word = wordFactory.convertWordDtoToWord(wordDto);
		
		Level l = levelDao.getLevelByLevel(level);
		
		List<Level> list = new ArrayList<Level>();
		
		list.add(l);
				
		word.setPmLevels(list);
		
		wordDao.save(word);
		
		return word.getId();
	}

	@Override
	public WordDto getWordByName(String name) throws Exception {
		
		WordDto wordDto =  null;
		
		Word word = wordDao.getWordByName(name);
		
		if (word != null) {
			wordDto = wordFactory.convertWordToWordDto(word);
		}
		
		return wordDto;
	}

	@Override
	public WordDto getWordsById(Integer id) throws Exception {
		
		WordDto wordDto =  null;
		
		Word word = wordDao.getWordsById(id);
		
		if (word != null) {
			wordDto = wordFactory.convertWordToWordDto(word);
		}
		
		return wordDto;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		boolean control = wordDao.delete(id);
		
		return control;
	}

	@Override
	public boolean update(WordDto wordDto) throws Exception {
		
		if (wordDto == null) {
			return false;
		}
		
		Word w = wordDao.getWordsById(wordDto.getId());
		
		w.setName(wordDto.getName());
		w.setActive(wordDto.isActive());
		
		wordDao.update(w);
		
		return true;
	}

	@Override
	public Integer saveWordLevel(Integer levelId, Integer wordId) throws Exception {
		Integer id = wordDao.saveWordLevel(levelId, wordId);
		return id;
	}

	


}
