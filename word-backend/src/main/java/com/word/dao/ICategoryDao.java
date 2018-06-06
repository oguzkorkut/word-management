package com.word.dao;

import java.util.List;

import com.word.model.Category;

public interface ICategoryDao {
	public boolean save(Category category)  throws Exception;
	
	public boolean update(Category category)  throws Exception;
	
	public List<Category> getCategories() throws Exception;
	
	public Category getCategoryById(Integer id) throws Exception;
	
	public Category getCategoryByCategoryName(String categoryName) throws Exception;
}
