package com.project.data;

import java.util.List;

import com.project.data.entity.CardEntity;

public interface DataAccessInterface<T> {
	public List<T> findAll();
	public CardEntity findById(String id);
	public boolean create(T t);
	public boolean update(T t);

}
