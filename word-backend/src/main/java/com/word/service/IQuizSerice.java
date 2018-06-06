package com.word.service;

import java.util.List;

import com.word.dto.AnswerDto;
import com.word.dto.CategoryDto;
import com.word.dto.QuestionDto;

public interface IQuizSerice {
	
	public boolean save(CategoryDto categoryDto) throws Exception;
	
	public boolean update(CategoryDto categoryDto) throws Exception;
	
	public List<CategoryDto> getCategories() throws Exception;
	
	public List<QuestionDto> getQuestionsByCategoryId(int id) throws Exception;
	
	public CategoryDto getCategoryByCategoryName(String categoryName) throws Exception;
	
	public boolean saveQuestion(QuestionDto questionDto) throws Exception;
	
	public boolean saveAnswer(AnswerDto answerDto) throws Exception;
	
	public boolean saveAnswers(List<AnswerDto> answerDtos) throws Exception;

}
