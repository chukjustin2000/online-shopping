package com.gutshaven.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gutshaven.shoppingbackend.dao.CategoryDAO;
import com.gutshaven.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	//private static List<Category> categories = new ArrayList<>();

	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		return query.getResultList();
	}

	/*
	 * Getting a single category
	 * @see com.gutshaven.shoppingbackend.dao.CategoryDAO#get(int)
	 */
	@Override
	public Category get(int id) {
		
	
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {
		try {
			//add category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}
	
	/*
	 *updating a single category
	 * @see com.gutshaven.shoppingbackend.dao.CategoryDAO#delete(com.gutshaven.shoppingbackend.dto.Category)
	 */
	@Override
	public boolean update(Category category) {
		try {
			//update category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	
	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		try {
			//update category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
