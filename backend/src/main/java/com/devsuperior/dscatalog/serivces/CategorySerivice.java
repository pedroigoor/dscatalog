package com.devsuperior.dscatalog.serivces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dtos.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRespository;

@Service
public class CategorySerivice {

	@Autowired
	private CategoryRespository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {

		List<Category> list = repository.findAll();
		return list.stream().map(CategoryDTO::new).collect(Collectors.toList());

	}
}
