package com.word.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LevelDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private int level;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private boolean active;
	private List<WordDto> words;

	public LevelDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<WordDto> getWords() {
		return words;
	}

	public void setWords(List<WordDto> words) {
		this.words = words;
	}

}