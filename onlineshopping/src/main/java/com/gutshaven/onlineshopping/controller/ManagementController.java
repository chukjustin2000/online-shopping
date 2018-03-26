package com.gutshaven.onlineshopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gutshaven.shoppingbackend.dao.CategoryDAO;
import com.gutshaven.shoppingbackend.dao.ProductDAO;
import com.gutshaven.shoppingbackend.dto.Category;
import com.gutshaven.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value = "/products", method= RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required = false) String operation ) {
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userclickManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product();
		
		//set few of the fields
		nProduct.setActive(true);
		nProduct.setSupplierId(1);
		
		mv.addObject("product", nProduct);
		
		if(operation != null) {
			
			if(operation.equals("product")) {
				mv.addObject("message", "Product Submitted Successfully!");
			}
		}
		return mv;
	}
	
	//handling product submission
	@RequestMapping(value = "/products", method= RequestMethod.POST)
	public String handleProductSubmission(@ModelAttribute("product") Product mProduct) {
		 
		logger.info(mProduct.toString());
		//create a new product record
		productDAO.add(mProduct);
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	// returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		
		return categoryDAO.list();
	}

}
