package com.word.service;

import java.util.List;

import com.word.dto.WordDto;

public interface IWordShuffleService {
	
	public void initiliaze() throws Exception;
	
	public void shuffle(List<WordDto> wordDtos) throws Exception;

	public void print();
	
	public String[][] getWords();
	
	public int[][] getWordPositions();

}
