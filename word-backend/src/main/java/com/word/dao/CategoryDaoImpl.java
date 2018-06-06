package com.word.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.model.Category;
import com.word.repository.CategoryRepository;

@Service("categoryDao")
@Transactional
public class CategoryDaoImpl implements ICategoryDao {

	private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean save(Category category) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveCategory begins. Category: ");
			sb.append(category.getCategory());
			logger.trace(sb.toString());
		}
		Category categoryResult = categoryRepository.save(category);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveCategory ends. ID: ");
			sb.append(categoryResult.getId());
			logger.trace(sb.toString());
		}
		return true;
	}

	@Override
	public boolean update(Category category) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("updateCategory begins. ID: ");
			sb.append(category.getId());
			logger.trace(sb.toString());
		}
		Category categoryResult = categoryRepository.save(category);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("updateCategory ends. ID: ");
			sb.append(categoryResult.getId());
			logger.trace(sb.toString());
		}

		return true;
	}

	@Override
	public List<Category> getCategories() throws Exception {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@Override
	public Category getCategoryById(Integer id) throws Exception {
		Category category = categoryRepository.findOne(id);
		return category;
	}

	@Override
	public Category getCategoryByCategoryName(String categoryName) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getCategoryByCategoryName begins. Name: ");
			sb.append(categoryName);
			logger.trace(sb.toString());
		}
		Category category = categoryRepository.findByCategory(categoryName);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getCategoryByCategoryName ends. ID: ");
			sb.append(category.getId());
			logger.trace(sb.toString());
		}
		return category;
	}

}
