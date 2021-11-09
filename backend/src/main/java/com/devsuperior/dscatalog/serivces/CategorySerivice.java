package com.devsuperior.dscatalog.serivces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRespository;

@Service
public class CategorySerivice {
	
	@Autowired
	private CategoryRespository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
}
