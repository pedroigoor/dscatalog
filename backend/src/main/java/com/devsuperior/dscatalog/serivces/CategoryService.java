package com.devsuperior.dscatalog.serivces;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dtos.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.excptions.DataBaseException;
import com.devsuperior.dscatalog.excptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.repositories.CategoryRespository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRespository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {

		List<Category> list = repository.findAll();
		return list.stream().map(CategoryDTO::new).collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));		
		return new CategoryDTO(entity);

	}

	@Transactional
	public CategoryDTO save(CategoryDTO dto) {
		return new CategoryDTO(repository.save(new Category(dto)));
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category enity = repository.getOne(id);
			enity.setName(dto.getName());
			enity = repository.save(enity);
			return new CategoryDTO(enity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não existente " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não existente " + id);
		} catch (DataIntegrityViolationException	 e) {
			throw new DataBaseException("Integrity violantion");
		}

	}

}
