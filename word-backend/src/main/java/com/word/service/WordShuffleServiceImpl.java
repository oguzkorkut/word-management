package com.word.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.word.dto.Index;
import com.word.dto.WordDto;

@Service("wordShuffleService")
public class WordShuffleServiceImpl implements IWordShuffleService {

	private static final Logger logger = LogManager.getLogger(WordShuffleServiceImpl.class);

	private int rows = 10;
	private int colls = 10;
	
	private String[][] words = null; // Ekranda goruntulenecek kelimeler
	private int[][] wordPositions = null;// Kelimelerin sirasi
	// private int[][] positionStatus = null;
	private int[][] weights = null; // Kelimelerin etrafindaki agirlik 1 arttirilacak
	private int[][] numberArray = null; // Indexlere numara verilecek

	// private Map<Integer, Index> positionMap = new HashMap<Integer, Index>();
	private Map<Integer, Index> numberMap = new HashMap<Integer, Index>();
	
	private Random random = new Random();
	
	@Override
	public void initiliaze() throws Exception {
		words = new String[rows][colls];
		wordPositions = new int[rows][colls];
		// positionStatus = new int[rows][colls];
		weights = new int[rows][colls];
		numberArray = new int[rows][colls];

		int count = 0;

		Index index = null;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colls; j++) {
				words[i][j] = "";
				wordPositions[i][j] = 0;
				// positionStatus[i][j] = 0;
				weights[i][j] = 1;
				numberArray[i][j] = count;

				index = new Index();

				index.setRow(i);
				index.setColl(j);

				numberMap.put(count, index);

				count++;
			}
		}
	}

	@Override
	public void shuffle(List<WordDto> wordDtos) throws Exception {

		StringBuilder builder = new StringBuilder();
		for (WordDto word : wordDtos) {
			builder.append(word.getName());
		}

		boolean isCompleted = true;
		boolean isFirstWord = true;

		for (int i = 0; i < wordDtos.size(); i++) {
			
			Index currentIndex = getRandomIndex(true, null, isFirstWord);

			isFirstWord = false;

			for (int j = 0; j < wordDtos.get(i).getName().length(); j++) {
				words[currentIndex.getRow()][currentIndex.getColl()] = String.valueOf(wordDtos.get(i).getName().charAt(j));

				wordPositions[currentIndex.getRow()][currentIndex.getColl()] = (i + 1) * 10 + j;

				weights[currentIndex.getRow()][currentIndex.getColl()] = -1;

				currentIndex = getRandomIndex(false, currentIndex, isFirstWord);

				if (currentIndex == null) {
					isCompleted = false;
					break;
				}
				reCalculateWeight(currentIndex);
				
			}

			if (currentIndex == null) {
				break;
			}
		}
		if (!isCompleted) {
			initiliaze();
			shuffle(wordDtos);
		}
	}
	
	private void reCalculateWeight(Index index) {
		int i = index.getRow();
		int j = index.getColl();

		weights[i][j] = -1;

		if (i - 1 >= 0 && j - 1 >= 0 && weights[i - 1][j - 1] != -1) {// (i - 1, j - 1)
			weights[i - 1][j - 1] = weights[i - 1][j - 1] + 1;
		}

		if (i - 1 >= 0 && weights[i - 1][j] != -1) {// (i - 1, j)
			weights[i - 1][j] = weights[i - 1][j] + 1;
		}

		if (i - 1 >= 0 && j + 1 < numberArray[0].length && weights[i - 1][j + 1] != -1) {// (i -1, j + 1)
			weights[i - 1][j + 1] = weights[i - 1][j + 1] + 1;
		}

		if (j - 1 >= 0 && weights[i][j - 1] != -1) {// (i, j - 1)
			weights[i][j - 1] = weights[i][j - 1] + 1;
		}

		if (j + 1 < numberArray[0].length && weights[i][j + 1] != -1) {// (i, j + 1)
			weights[i][j + 1] = weights[i][j + 1] + 1;
		}

		if (i + 1 < numberArray.length && j - 1 >= 0 && weights[i + 1][j - 1] != -1) {// (i + 1, j - 1)
			weights[i + 1][j - 1] = weights[i + 1][j - 1] + 1;
		}

		if (i + 1 < numberArray.length && weights[i + 1][j] != -1) {// (i + 1, j)
			weights[i + 1][j] = weights[i + 1][j] + 1;
		}

		if (i + 1 < numberArray.length && j + 1 < numberArray[0].length && weights[i + 1][j + 1] != -1) {// (i + 1, j +
																											// 1)
			weights[i + 1][j + 1] = weights[i + 1][j + 1] + 1;
		}
	}

	private Index getRandomIndex(boolean isStart, Index currentIndex, boolean isFirstWord) {
		Index index = null;

		Map<Integer, Index> randomMap = new HashMap<Integer, Index>();

		if (isStart) {
			for (int i = 0; i < numberArray.length; i++) {
				for (int j = 0; j < numberArray.length; j++) {
					if (weights[i][j] != -1) {
						for (int n = 0; n < weights[i][j]; n++) {

							if (!isFirstWord) {
								if (findPositionByIndex(getIndexByPosition(numberArray[i][j]))) {
									randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i][j]));
								}
							} else {
								randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i][j]));
							}
						}
					}
				}
			}

		} else {
			int i = currentIndex.getRow();
			int j = currentIndex.getColl();

			if (i - 1 >= 0 && j - 1 >= 0 && weights[i - 1][j - 1] != -1) {// (i - 1, j - 1)
				for (int n = 0; n < weights[i - 1][j - 1]; n++) {
					randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i - 1][j - 1]));
				}
			}

			if (i - 1 >= 0 && weights[i - 1][j] != -1) {// (i - 1, j)
				for (int n = 0; n < weights[i - 1][j]; n++) {
					randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i - 1][j]));
				}
			}

			if (i - 1 >= 0 && j + 1 < numberArray[0].length && weights[i - 1][j + 1] != -1) {// (i -1, j + 1)
				for (int n = 0; n < weights[i - 1][j + 1]; n++) {
					randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i - 1][j + 1]));
				}
			}

			if (j - 1 >= 0 && weights[i][j - 1] != -1) {// (i, j - 1)
				for (int n = 0; n < weights[i][j - 1]; n++) {
					randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i][j - 1]));
				}
			}

			if (j + 1 < numberArray[0].length && weights[i][j + 1] != -1) {// (i, j + 1)
				for (int n = 0; n < weights[i][j + 1]; n++) {
					randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i][j + 1]));
				}
			}

			if (i + 1 < numberArray.length && j - 1 >= 0 && weights[i + 1][j - 1] != -1) {// (i + 1, j - 1)
				for (int n = 0; n < weights[i + 1][j - 1]; n++) {
					randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i + 1][j - 1]));
				}
			}

			if (i + 1 < numberArray.length && weights[i + 1][j] != -1) {// (i + 1, j)
				for (int n = 0; n < weights[i + 1][j]; n++) {
					randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i + 1][j]));
				}
			}

			if (i + 1 < numberArray.length && j + 1 < numberArray[0].length && weights[i + 1][j + 1] != -1) {// (i + 1,
																												// 1)
				for (int n = 0; n < weights[i + 1][j + 1]; n++) {
					randomMap.put(randomMap.size(), getIndexByPosition(numberArray[i + 1][j + 1]));
				}
			}
		}

		int position = 0;
		if (randomMap.size() != 0) {
			position = random.nextInt(randomMap.size());

			index = randomMap.get(position);
		}

		return index;
	}

	private Index getIndexByPosition(int position) {
		Index index = null;
		for (int i = 0; i < numberArray.length; i++) {
			if (index != null) {
				break;
			}
			for (int j = 0; j < numberArray.length; j++) {
				if (numberArray[i][j] == position) {
					index = new Index();
					index.setRow(i);
					index.setColl(j);
					break;
				}
			}
		}

		return index;
	}

	private boolean findPositionByIndex(Index index) {
		int i = index.getRow();
		int j = index.getColl();

		if (i - 1 >= 0 && j - 1 >= 0 && weights[i - 1][j - 1] == -1) {// (i - 1, j - 1)
			return true;
		}

		if (i - 1 >= 0 && weights[i - 1][j] == -1) {// (i - 1, j)
			return true;
		}

		if (i - 1 >= 0 && j + 1 < numberArray[0].length && weights[i - 1][j + 1] == -1) {// (i -1, j + 1)
			return true;
		}

		if (j - 1 >= 0 && weights[i][j - 1] == -1) {// (i, j - 1)
			return true;
		}

		if (j + 1 < numberArray[0].length && weights[i][j + 1] == -1) {// (i, j + 1)
			return true;
		}

		if (i + 1 < numberArray.length && j - 1 >= 0 && weights[i + 1][j - 1] == -1) {// (i + 1, j - 1)
			return true;
		}

		if (i + 1 < numberArray.length && weights[i + 1][j] == -1) {// (i + 1, j)
			return true;
		}

		if (i + 1 < numberArray.length && j + 1 < numberArray[0].length && weights[i + 1][j + 1] == -1) {// (i + 1, j +
			return true;
		}
		return false;
	}
	
	@Override
	public void print() {
		StringBuilder builderW = new StringBuilder();
		
		for (String[] wordArry : words) {

			for (String word : wordArry) {
				if (word == null || word.trim() == "") {
					builderW.append("- ");
					
				} else {
					builderW.append(word).append(" ");
				}
			}
			builderW.append("\n");
		}
		
		for (int[] wordPositionArry : wordPositions) {

			for (int wordPosition : wordPositionArry) {
				if (wordPosition == 0) {
					builderW.append("X  ");
				} else {
					builderW.append(wordPosition).append(" ");
				}
			}
			builderW.append("\n");
		}
		logger.info(builderW.toString());
	}
	
	@Override
	public String[][] getWords() {
		return words;
	}

	@Override
	public int[][] getWordPositions() {
		return wordPositions;
	}
}
