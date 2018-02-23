package com.gutshaven.shoppingbackend.dao;

import java.util.List;

import com.gutshaven.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
	
	public List<Category> list();
	Category get(int id); 

}
