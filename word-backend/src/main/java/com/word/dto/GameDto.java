package com.word.dto;

import java.io.Serializable;
import java.util.List;

public class GameDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int level;

	private List<WordDto> word;

	private boolean passed;

	private String[][] words = null; // Ekranda goruntulenecek kelimeler
	private int[][] wordPositions = null;// Kelimelerin sirasi

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<WordDto> getWord() {
		return word;
	}

	public void setWord(List<WordDto> word) {
		this.word = word;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String[][] getWords() {
		return words;
	}

	public void setWords(String[][] words) {
		this.words = words;
	}

	public int[][] getWordPositions() {
		return wordPositions;
	}

	public void setWordPositions(int[][] wordPositions) {
		this.wordPositions = wordPositions;
	}

}
