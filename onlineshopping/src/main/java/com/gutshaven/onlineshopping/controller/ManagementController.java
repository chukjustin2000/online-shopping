package com.gutshaven.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gutshaven.onlineshopping.util.FileUploadUtility;
import com.gutshaven.onlineshopping.validator.ProductValidator;
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
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, 
			HttpServletRequest request) {
		
		new ProductValidator().validate(mProduct, results);
		 
		//Check if there are any errors
		if(results.hasErrors()) {
			
			model.addAttribute("userclickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission");
			
			return "page";
		}
		
		
		logger.info(mProduct.toString());
		//create a new product record
		productDAO.add(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	// returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		
		return categoryDAO.list();
	}

}
