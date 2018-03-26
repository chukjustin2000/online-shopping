package com.gutshaven.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gutshaven.shoppingbackend.dao.CategoryDAO;
import com.gutshaven.shoppingbackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category; 
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.gutshaven.shoppingbackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	
	/*@Test
	public void testAddCategory() {
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is some description for television");
		category.setImageUrl("CAT_1.png");
		
		assertEquals("Successfully added a category inside a table", true, categoryDAO.add(category));
	}*/

	/*@Test
	public void testGetCategory() {
		category = categoryDAO.get(1);
		
		
		
		assertEquals("Successfully fetched a single category from the table", "Television", category.getName());
	}*/
	/*
	@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(1);
		
		category.setName("TV");
		
		assertEquals("Successfully updated a single category in the table", true, categoryDAO.update(category));
	}*/
	
	/*@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(1);
		
		
		assertEquals("Successfully deleted a single category in the table", true, categoryDAO.delete(category));
	}*/
	
	/*@Test
	public void testListCategory() {
		category = categoryDAO.get(1);
		
		
		assertEquals("Successfully fetched the list of categories from the table", 3, categoryDAO.list().size());
	}*/
	
	@Test
	public void testCRUDCategory() {
		// add operations
		category = new Category();

		category.setName("Laptop");
	    category.setDescription("This is some description for Laptop");
		category.setImageUrl("CAT_1.png");

		assertEquals("Something went wrong while inserting a new category!", true, categoryDAO.add(category));
		category = new Category();

		category.setName("Television");
		category.setDescription("This is some description for television");
		category.setImageUrl("CAT_2.png");

		assertEquals("Something went wrong while inserting a new category!", true, categoryDAO.add(category));
		
		
		
		category.setName("TV");
				  
		assertEquals("Successfully updated a single category in the table", true,
				categoryDAO.update(category));

		
	    //fetching and updating the category
		category = categoryDAO.get(2);
		
		category.setName("TV");
		
		assertEquals("Something went wrong while updating the existing record!", true, categoryDAO.update(category));
		
		
		//delete the category
		assertEquals("Something went wrong while deleting the existing record!", true, categoryDAO.delete(category));
		
		//fetching the list of categories
		assertEquals("Something went wrong while fetching the list of catefories!", 1, categoryDAO.list().size());
	}
}
