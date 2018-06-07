package com.word.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.word.dto.LevelDto;
import com.word.dto.WordDto;
import com.word.model.Level;
import com.word.model.Word;

@Service("wordFactory")
public class WordFactory {

	private static final Logger logger = LogManager.getLogger(WordFactory.class);
	
	public LevelDto convertLevelToLevelDto(Level level) {
		if (level == null) {
			logger.error("convertLevelToLevelDto -> level is null");
			return null;
		}
		LevelDto levelDto = new LevelDto();

		BeanUtils.copyProperties(level, levelDto);

		if (!CollectionUtils.isEmpty(level.getWords())) {

			WordDto wordDto = null;
			
			List<Word> words = new ArrayList<Word>(level.getWords());

			for (Word word : words) {

				wordDto = new WordDto();

				BeanUtils.copyProperties(word, wordDto);
				
				levelDto.getWords().add(wordDto);
			}
		}

		return levelDto;
	}

	public Level convertLevelDtoToLevel(LevelDto levelDto) {
		
		if (levelDto == null) {
			logger.error("convertCategoryDtoToCategory -> levelDto is null");
			return null;
		}
		Level level = new Level();

		BeanUtils.copyProperties(levelDto, level);

		if (!CollectionUtils.isEmpty(levelDto.getWords())) {
			Word word = null;

			for (WordDto wordDto : levelDto.getWords()) {
				word = new Word();

				BeanUtils.copyProperties(wordDto, word);

				level.getWords().add(word);
			}
		}
		return level;
	}

	public WordDto convertWordToWordDto(Word word) {
		if (word == null) {
			logger.error("convertWordToWordDto -> word is null");
			return null;
		}
		WordDto wordDto = new WordDto();

		BeanUtils.copyProperties(word, wordDto);

		return wordDto;
	}
	
	
	public Word convertWordDtoToWord(WordDto wordDto) {
		if (wordDto == null) {
			logger.error("convertWordDtoToWord -> wordDto is null");
			return null;
		}
		Word word = new Word();

		BeanUtils.copyProperties(wordDto, word);

		return word;
	}
	

}
